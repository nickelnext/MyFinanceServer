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

<form method="post" action="MainServlet">
       <p> Username:<input type="text" name="username"></p>
        <p>Password:<input type="password" name="password"></p>
        <p> JSON:<input type="text" name="json"></p>
      <p><input type="submit" value="MainServlet"></p>
</form>
<form method="get" action="VersionServlet">
<p> Client Version<input type="text" name="clientVersion"></p>
      <p><input type="submit" value="Version"></p>
</form>


<form method="post" action="HistoryServlet">
        <p> ISIN:<input type="text" name="ISIN"></p>
      <p><input type="submit" value="Get History"></p>
</form>

</body>
</html>


                
