<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%    
    String fail = request.getParameter("fail");
    boolean failS = fail!=null && fail.equals("true");
%>
<!DOCTYPE html>
<html>

    <head>
        <title>GondorTree | REGISTRAR</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" >
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
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
                        <form method="POST" action="member.htm?action=register">
                            <div class="input-group mb-3">
                                <div class="input-group-append">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="email" id="email" name="email" class="form-control input_user" placeholder="email" required>
                            </div>
                            <div class="input-group mb-2">
                                <div class="input-group-append">
                                    <span class="input-group-text"><i class="fas fa-book"></i></span>
                                </div>
                                <input type="text" id="name" name="name" class="form-control input_pass" placeholder="Primeiro Nome" required>
                            </div>
                            <div class="input-group mb-2">
                                <div class="input-group-append">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" id="password" name="password" class="form-control input_pass" placeholder="Senha" required>
                            </div>
                            <div class="input-group mb-2">
                                <div class="input-group-append">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" id="password_ver" class="form-control input_pass" placeholder="Confirmar senha" required>
                            </div>
                            <div class="d-flex justify-content-center mt-3 login_container">
                                <button type="submit" class="btn login_btn register_btn">
                                    Registrar
                                </button>
                            </div>
                        </form>
                    </div>

                    <div class="mt-4">
                        <div class="d-flex justify-content-center links">
                            Já possui conta? <a href="member.htm?action=login" class="ml-2">Login</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script type="text/javascript">
            $("[data-hide]").on("click", function() {
              $("." + $(this).attr("data-hide")).hide();
            });
            <% if(failS){ %>
                $(".alert").toggle().addClass('btn-danger');
                $(".alert #resposta").html("Ops!! <br/> Parece que já existe um registro com esse E-mail!");
            <% 
                }
            %>
            var password = document.getElementById("password")
            , confirm_password = document.getElementById("password_ver");
            function validatePassword(){
                if(password.value != confirm_password.value) {
                  confirm_password.setCustomValidity("Senhas diferentes!");
                } else {
                  confirm_password.setCustomValidity('');
                }
              }

              password.onchange = validatePassword;
              confirm_password.onkeyup = validatePassword;
        </script>

    </body>    
</html>