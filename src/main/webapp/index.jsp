<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	Index
	<%=new Date()%>
	<ol>	
                <li><a href="./mvc/case01/hello/welcome">第一個springmvc網頁，取得字串資料</a></li>
		<li><a href="./mvc/case01/hello/sayhi?name=Jay&age=20">使用queryString</a></li>
		<li><a href="./mvc/case01/hello/bmi?h=170&w=60">BMI計算</a></li>
		<li><a href="./mvc/case01/hello/exam/75">PathVariable使用</a></li>
		<li><a href="./mvc/case01/hello/calc/add?x=30&y=20">PathVariable+RequestParam</a></li>
		<li><a href="./mvc/case01/hello/path/namejo/java8">路徑*及?使用</a></li>
		<li><a href="./mvc/case01/hello/age?age=18&age=19&age=20">接收多筆參數的應用</a></li>
		<li><a
			href="./mvc/case01/hello/javaexam?score=80&score=100&score=50">接收多筆參數並計算最高分、最低分、平均、加總</a></li>
		<li><a href="./mvc/case01/hello/user?name=John&age=18">自動匹配User物件</a></li>
		<li><a
			href="./mvc/case01/hello/person?name=John&score=100&age=18&pass=true">在RequestParam中使用map來接收不同參數數量、型別的資料</a></li>
		<li><a href="./mvc/case02/lotto/">樂透選號程式</a></li>	
                <li><a href="./mvc/case03/exam/">sp:form</a></li>
	        <li><a href="./mvc/case04/person/">sp:form+驗證</a></li>
		<li><a href="./mvc/case04/stock/">Stock買賣+自訂驗證</a></li>
	</ol>
</body>
<!--  -->
</html>