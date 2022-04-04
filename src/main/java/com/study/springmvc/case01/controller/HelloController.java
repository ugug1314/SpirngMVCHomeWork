package com.study.springmvc.case01.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springmvc.case01.entity.User;

@Controller
// Base路徑:/case01/hello
@RequestMapping("/case01/hello")
public class HelloController {

//#
	// 1. 取得字串資料
	// 路徑:/welcome
	@RequestMapping("/welcome")
	@ResponseBody // 將內容存在responsebody中而不是ViewResolver去找view
	public String welcome() {
		// 取得本方法的名稱
		String mymethodname = Thread.currentThread().getStackTrace()[1].getMethodName();
		return "Hello SpringMVC " + new Date();
	}

//#
	// 2. 帶參數(?xxx=xxx 配置@RequestParam)queryString
	// 路徑:/sayhi?name=Jay&age=20 or /hi?name=Jay&age=20
	@RequestMapping(value = { "/sayhi", "/hi" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody // 直接根據回傳值型別回應
	public String sayhi(@RequestParam(value = "name") String name,
			// 設定age可以不輸入，預設值為0
			@RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {

		return String.format("Hi %s %d", name, age);
	}

//#	
	// 3. ?後帶入參數並計算(Lab 練習)
	// 路徑:/bmi?h=170.0&w=60.0
	// 請設計方法api,結果會得到bmi=20.76,若資訊不足則不顯示
	@RequestMapping(value = { "/bmical" }, method = { RequestMethod.GET })
	@ResponseBody
	public String bmical(@RequestParam(value = "height", required = false) double height,
			@RequestParam(value = "weight", required = false) double weight) {
		Optional<Double> bmi = Optional.of(weight / (Math.pow(height / 100, 2)));
		
		return String.format("BMI= %s", bmi.orElse(0.0));

	}

	// 老師的寫法
	@GetMapping("/bmi")
	@ResponseBody
	public String bmical(@RequestParam(value = "h", required = false) Optional<Double> h,
			@RequestParam(value = "w", required = false) Optional<Double> w) {
		String result = "None";
		if (h.isPresent() && w.isPresent()) {
			double bmi = w.get() / Math.pow(h.get() / 100, 2);
			result = String.format("%.2f", bmi);
		}
		return String.format("BMI= %s", result);
	}

//#	
	/*
	 * 4. 路徑參數 @PathVariable 路徑：/exam/75 => score: 75 pass 路徑：/exam/45 => score: 45
	 * fail
	 */
	@GetMapping("/exam/{score}")
	@ResponseBody
	public String examScore(@PathVariable("score") Integer score) {
		return String.format("score: %d %s", score, (score >= 60) ? "Pass" : "Fail");
	}

//#	
	/*
	 * 5. @RequestParam + @PathVariable (Lab 練習) 子路徑：/calc/add?x=30&y=20 ->
	 * Result：50 子路徑：/calc/sub?x=30&y=20 -> Result：10 子路徑：/calc/sub?y=20 ->
	 * Result：-20 子路徑：/calc/add -> Result：0
	 */
	// 請設計方法api
	@GetMapping("/calc/{domethod}") // 以路徑參數來判斷是用加還是減
	@ResponseBody
	// 實務上參數最好使用optional
	public String calc(@PathVariable("domethod") String domethod,
			@RequestParam(value = "x", required = false, defaultValue = "0") Integer x,
			@RequestParam(value = "y", required = false, defaultValue = "0") Integer y) {
		int result = 0;

		switch (domethod) {
		case "add":
			result = x + y;
			break;
		case "sub":
			result = x - y;
			break;
		default:
			return "method error";
		}

		return String.format("Result: %d %s %d = %d", x, (domethod.equals("add")) ? "+" : "-", y, result);
	}

//#	
	/*
	 * 6. @PathVariable (萬用字元： * 任意多字、? 任意一字) 子路徑：/path/namejo/java8
	 * 子路徑：/path/nameTaipei/java7 子路徑：/path/name1234/java6
	 */
	@GetMapping("/path/name*/java?")
	@ResponseBody
	public String any() {

		return "Path OK!";
	}

//#
	/*
	 * 7. 多筆參數資料 子路徑：/age?age=18&age=19&age=20 並計算平均 avg of age = 19.0
	 */
	@GetMapping("/age")
	@ResponseBody
	public String age(@RequestParam("age") List<Integer> ageList) {
		double avgOfAge = ageList.stream().mapToInt(Integer::intValue).average().getAsDouble();

		return String.format("avg of age= %.1f", avgOfAge);
	}

//#
	/*
	 * 8. 得到多筆Java考試成績的資料 (Lab 練習) 路徑:/javaexam?score=80&score=100&score=50
	 * 求出最高分、最低分、平均與總分
	 */
	@GetMapping("/javaexam")
	@ResponseBody
	public String scorescalc(@RequestParam("score") List<Integer> scoreList) {
		IntSummaryStatistics stat = scoreList.stream().mapToInt(Integer::intValue).summaryStatistics();

		return String.format("hight: %d, low: %d ,avg: %.1f,sum= %d", stat.getMax(), stat.getMin(), stat.getAverage(),
				stat.getSum());
	}

//#
	/*
	 * 9.pojo(entity) 有一個User.java 裡面有name與age這兩個物件屬性 子路徑:/user?name=John&age=18
	 * 可以進行參數自動匹配
	 */
	@GetMapping(value = "/user")
	@ResponseBody
	public String getUser(User user) {
		return user.toString();
	}

//#
	/*
	 * Map 參數 子路徑：/person?name=John&score=100&age=18&pass=true
	 * 子路徑：/person?name=Mary&score=90&age=20&level=2 常用於form 表單傳來的參數是不一致的
	 */
	@GetMapping(value = "/person")
	@ResponseBody
	// 第一個寫法不好用，不要用
	// public String getPerson(User user,@RequestParam(value="score",required =
	// false)Integer score) {
	// 直接轉為map來處理
	public String getPerson(@RequestParam Map<String, String> person) throws IOException {

		return person.toString();
	}

//#
	/*
	 * 11.獲取原生 HttpSession 的資料
	 */
	@GetMapping(value = "/sessioninfo", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String getSessionInfo(HttpSession httpSession) {
		String sessionKey = "firstAccessTime";
		Object time = httpSession.getAttribute(sessionKey);
		if (time == null) {
			time = new Date();
			httpSession.setAttribute(sessionKey, time);
		}
		return String.format("firstAccessTime: %s\nsessionid: %s", time, httpSession.getId());
	}
}
//#

//因要測試用xml設定，固已先將webAppInitializer及WebConfig改為txt檔，之後要使用時再變回java檔