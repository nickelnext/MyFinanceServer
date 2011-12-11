<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pinella</title>
</head>
<body>
<% java.util.Date d = new java.util.Date(); %>
<h1>
<%= d.toString() %> 
</h1>
<h2> Oh, mettete una string json valida, senò è un casino...</h2>
<h3> se non avete idee, provate con questa: </h3> 
<p>[{"idCode":"IT0004572910","reqType":"UPDATE","quotType":"BOND","preferredSite":"__NONE__"},{"idCode":"IT0004719297","reqType":"UPDATE","quotType":"BOND","preferredSite":"
Borsaitaliana_it"},{"idCode":"IT0004220627","reqType":"QUOTATION"},{"idCode":"IT0003926547","reqType":"QUOTATION"},{"idCode":"IT0001233417","reqType":"QUOTATION"},{"idCod
e":"LU0336083497","reqType":"QUOTATION"},{"idCode":"US38259P5089","reqType":"QUOTATION"},{"idCode":"IT0003406334","reqType":"QUOTATION"},{"idCode":"IT0004168826","reqType
":"QUOTATION"},{"idCode":"IT0000382983","reqType":"QUOTATION"}]</p> 
<form method="post" action="MainServlet">
       <p> Username:<input type="text" name="username"></p>
        <p>Password:<input type="password" name="password"></p>
        <p> JSON:<input type="text" name="json"></p>
      <p><input type="submit" value="MainServlet"></p>
</form>
</body>
</html>


                
