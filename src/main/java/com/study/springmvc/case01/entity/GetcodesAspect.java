package com.study.springmvc.case01.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.el.stream.Stream;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class GetcodesAspect {
	//指定檔案路徑
	private String p = "C:\\Users\\user\\Desktop\\2022.02.20SpringCore\\springmvc\\src\\main\\java\\com\\study\\springmvc\\case01\\controller\\HelloController.java";
     //指定要攔截的方法
	@Pointcut(value="execution(* com.study.springmvc.case01.controller.HelloController.*(..))")
	public void getMethodCode() {
	}
	@Around(value="getMethodCode()")
	public Object dogetCodes(ProceedingJoinPoint jp) {
	//取得方法名稱
			String methodName=jp.getSignature().getName();
		    String temp = null;
			String codes =null;
			String str;
			try {
				FileReader fr = new FileReader(p);
				BufferedReader br = new BufferedReader(fr);
				while ((str = br.readLine()) != null) {
					codes += str + "\n";
				}
				//宣告字串抓取起始與結束屬性
				int startb = 0;
				int rstart = 0;
				startb = codes.indexOf(methodName);  //取得攔截方法開始的索引
				temp = codes.substring(0, startb);   //減少字串範圍到方法開始的索引
				rstart = temp.lastIndexOf("#") + 1;  //取得自取設定的分段符號位置當真正的開始位置
				temp = codes.substring(rstart);      //取得方法開始之後的字串
				int end = 0;

				end = temp.indexOf("#") - 2;        //取得下一個方法的開始索引
				codes = temp.substring(0, end);    //依上述開始及結束索引取得方法的程式碼
				
				Object result=jp.proceed();        //先讓攔截方法先接收被攔截方法的返回值
				return result.toString()+"<br><br><hr>程式碼如下<pre>"+codes+"</pre";    //加上讀取到的程式碼後返回給瀏覽器
				
			} catch (Throwable e) {

				System.out.println("some codes can't catch");
			}
			 return null;
		}
	}


