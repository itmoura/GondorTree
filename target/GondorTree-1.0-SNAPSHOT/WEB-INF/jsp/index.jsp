<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>

    <input type="hidden" id="id">
    Email: <input type="email" id="email" required="required" name="email"><br>
    Senha: <input type="password" id="password" required="required" name="password"><br>
    <button onclick="submit();">Submit</button>

    <table id="table" border=1>
        <tr> 
            <th> Name </th> 
            <th> Email </th> 
            <th> Edit </th> 
            <th> Delete </th> 
        </tr>
    </table>
	
</body>
</html>