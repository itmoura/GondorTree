<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String link = "/GondorTree/";
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
                        <form>
                            <div class="input-group mb-3">
                                <div class="input-group-append">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="email" id="email" name="email" class="form-control input_user" placeholder="email" required>
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
                                <a class="btn login_btn register_btn" onclick="submit();">
                                    Registrar
                                </a>
                            </div>
                        </form>
                    </div>

                    <div class="mt-4">
                        <div class="d-flex justify-content-center links">
                            Já possui conta? <a href="<%=link%>user/" class="ml-2">Login</a>
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
            submit = function () {
                if ($('#email').val() == "" || $('#password').val() == "") {
                    $(".alert").toggle().addClass('btn-danger');
                    $(".alert #resposta").text("Todos campos são obrigatorios!!");
                    setTimeout(function(){ 
                        $(".alert").css("display", "none");
                    }, 3000);
                } else {
                    if ($('#password').val() != $('#password_ver').val()) {
                        $(".alert").toggle().addClass('btn-danger');
                        $(".alert #resposta").text("Senhas não conhecidem");
                        $('#password').val("");
                        $('#password_ver').val("");
                        setTimeout(function(){ 
                            $(".alert").css("display", "none");
                        }, 3000);
                    } else {
                        $(".register_btn").prop("onclick", null).off("click");
                        $(".login_btn").css("cursor", "no-drop");
                        $.ajax({
                            async: false,
                            url: '<%=link%>user/saveOrUpdate',
                            type: 'POST',
                            data: {
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
                                $(".register_btn").click(submit);
                                $(".login_btn").css("cursor", "pointer");
                                if(response.data != 0){
                                    $.ajax({
                                        url: '<%=link%>member/saveOrUpdate',
                                        type: 'POST',
                                        data: {
                                            id: response.data,
                                            name: $('#email').val()
                                        }, success: function (response) {
                                            if(response.status == 200)
                                                window.location.href = "<%=link%>user/";
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }
        </script>

    </body>    
</html>