<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String link = "/GondorTree/";
%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<!DOCTYPE html>
<html>

    <head>
        <title>GondorTree | LOGIN</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    </head>
    <body onload="load();">

        <input type="hidden" id="id">
        Nome: <input type="text" id="name" required="required" name="name"><br>
        <button onclick="submit();">Submit</button>

        <table id="table" border=1>
            <tr> <th> Name </th> <th> Edit </th> <th> Delete </th> </tr>

        </table>

        <script type="text/javascript">
            data = "";
            submit = function () {

                $.ajax({
                    url: '<%=link%>blazon/saveOrUpdate',
                    type: 'POST',
                    data: {id: $("#id").val(),name: $('#name').val()},
                    success: function (response) {
                        alert(response.message);
                        load();
                    }
                });
            }

            delete_ = function (id) {
                $.ajax({
                    url: '<%=link%>blazon/delete',
                    type: 'POST',
                    data: {id: id},
                    success: function (response) {
                        alert(response.message);
                        load();
                    }
                });
            }


            edit = function (index) {
                $("#id").val(data[index].id);
                $("#name").val(data[index].name);
            }

            load = function () {
                $.ajax({
                    url: '<%=link%>/blazon/list',
                    type: 'POST',
                    success: function (response) {
                        data = response.data;
                        $('.tr').remove();
                        for (i = 0; i < response.data.length; i++) {
                            $("#table").append("<tr class='tr'> <td> " + response.data[i].name + " </td> <td> <a href='#' onclick= edit(" + i + ");> Edit </a>  </td> </td> <td> <a href='#' onclick='delete_(" + response.data[i].id + ");'> Delete </a>  </td> </tr>");
                        }
                    }
                });

            }

        </script>

    </body>
</html>