<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String link = "/GondorTree/";
%>
<!DOCTYPE html>
<html>

    <head>
        <title>GondorTree | MEMBER</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    </head>
    <body onload="load();">

        <input type="hidden" id="id">
        Nome: <input type="text" id="name" required="required" name="name"><br>
        Descrição: <input type="text" id="description" required="required" name="description"><br>
        <button onclick="submit();">Submit</button>
        
        <a href="<%=link%>blazon/">Cadastrar Brazão familiar</a>

        <table id="table" border=1>
            <tr> 
                <th> Name </th> 
                <th> Descrição </th> 
                <th> Edit </th> 
            </tr>
        </table>

        <script type="text/javascript">
            data = "";
            submit = function () {
                $.ajax({
                    url: '<%=link%>member/saveOrUpdate',
                    type: 'POST',
                    data: {id: $("#id").val(),name: $('#name').val(),description: $('#description').val()},
                    success: function (response) {
                        alert(response.message);
                        load();
                    }
                });
            }

            delete_ = function (id) {
                $.ajax({
                    url: '<%=link%>member/delete',
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
                $("#description").val(data[index].description);
            }

            load = function () {
                $.ajax({
                    url: '<%=link%>member/list',
                    type: 'POST',
                    success: function (response) {
                        data = response.data;
                        $('.tr').remove();
                        for (i = 0; i < response.data.length; i++) {
                            $("#table").append("<tr class='tr'> <td> " + response.data[i].name + " </td> <td> " + response.data[i].description + " </td> <td> <a href='#' onclick= edit(" + i + ");> Edit </a>  </td> </td>  </tr>");
                        }
                    }
                });

            }

        </script>

    </body>
</html>