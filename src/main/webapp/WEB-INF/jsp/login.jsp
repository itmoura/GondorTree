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
    <body class="displayLogin">
        
        <div class="alert text-center displayAlert" role="alert">
            <a href="#" class="close" data-hide="alert">&times;</a>
            <div id="resposta"></div>
        </div>

        <div class="container h-100">
            <div class="d-flex justify-content-center h-100">
                <div class="user_card">
                    <div class="d-flex justify-content-center">
                        <div class="brand_logo_container">
                            <img src="<c:url value="/resources/img/logo.jpg" />" class="brand_logo" alt="Logo Gondor Tree">
                        </div>
                    </div>
                    <div class="d-flex justify-content-center form_container">
                        <form>
                            <div class="input-group mb-3">
                                <div class="input-group-append">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="email" id="email" name="email" class="form-control input_user" placeholder="email">
                            </div>
                            <div class="input-group mb-2">
                                <div class="input-group-append">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" id="password" name="password" class="form-control input_pass" placeholder="password">
                            </div>
                            <!-- 
                            <div class="form-group">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="customControlInline">
                                    <label class="custom-control-label" for="customControlInline">Remember me</label>
                                </div>
                            </div>
                            -->
                            <div class="d-flex justify-content-center mt-3 login_container">
                                <a class="btn login_btn" onclick="submit();">Login</a>
                            </div>
                        </form>
                    </div>

                    <div class="mt-4">
                        <div class="d-flex justify-content-center links">
                            Não possui uma conta? <a href="<%=link%>/user/register" class="ml-2">Registre</a>
                        </div>
                        <div class="d-flex justify-content-center links">
                            <a href="#">Esqueceu Senha?</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            $("[data-hide]").on("click", function() {
              $("." + $(this).attr("data-hide")).hide();
            });
            submit = function () {
                if ($('#email').val() == "" || $('#password').val() == "") {
                    $(".alert").toggle().addClass('btn-danger');
                    $(".alert #resposta").text("Todos campos são obrigatorios!!");
                    setTimeout(function(){ 
                        $(".alert").css("display", "none");
                    }, 3000);
                } else {
                    $(".login_btn").prop("onclick", null).off("click");
                    $(".login_btn").css("cursor", "wait");
                    $.ajax({
                        url: '<%=link%>/user/login',
                        type: 'POST',
                        data: {
                            id: $("#id").val(),
                            password: $('#password').val(),
                            email: $('#email').val()
                        }, success: function (response) {
                            if(response.status != 200)
                                $(".alert").toggle().addClass('btn-warning');
                            else 
                                $(".alert").toggle().addClass('btn-success');
                            $(".alert #resposta").text(response.message);
                            setTimeout(function(){ 
                                $(".alert").css("display", "none");
                            }, 3000);
                            $(".login_btn").click(submit);
                            $(".login_btn").css("cursor", "pointer");
                            if(response.status == 200)
                                window.location.href = "<%=link%>member/"; 
                        }
                    });
                }
            }
        </script>

    </body>    
</html>