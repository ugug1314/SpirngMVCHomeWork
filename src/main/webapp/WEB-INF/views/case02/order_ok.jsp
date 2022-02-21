<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order OK</title>
</head>
<body>
    name:${name }<p />
    price:${price }<p />
    qty:${qty }<p />
  <hr>
  參數是放在request.cope中的  <p />
     name:${requestScope.name }<p />
    price:${requestScope.price }<p />
    qty:${requestScope.qty }<p />
</body>
</html>