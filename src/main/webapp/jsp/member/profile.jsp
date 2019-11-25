<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.gondortree.model.*"%>
<%
    Member usess = (Member) session.getAttribute("user");
    Member ref = (Member) request.getAttribute("bean");

    String id = request.getParameter("id");
    Long idc = new Long(id) / 19819;
    boolean libEdit = false;
    if (idc == usess.getId().longValue()) {
        libEdit = true;
    }

    String fail = request.getParameter("fail");
    boolean failS = fail != null && fail.equals("true");
%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>

    <head>
        <title>GondorTree | Perfil</title>
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">    
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"> 
    </head>
    <script>
        window.onload = function () {

            $.getJSON("member.htm?action=jsonFamily&member=<%=ref.getId()%>", function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#family").append($("<option>", {value: data[i].id, text: data[i].familyName}));
                }
        <% if (ref.getFamilyId() != null) {%>
                $("#family").val(<%=ref.getFamilyId().getId()%>).trigger("change");
        <% }%>
            });
        }
    </script>
    <%@ include file="/includes/navbar.jsp" %>
    <body>
        <div class="alert text-center displayAlert" role="alert">
            <a href="#" class="close" data-hide="alert">&times;</a>
            <div id="resposta"></div>
        </div>
        
        <div class="container">
            <div class="row">
                <div class="bgFundoPerfil" style="background-image: url('https://storage.cloud.google.com/gondor-tree/profile/<%=ref.getCoverPhoto()%>');">
                    <div class="position-relative profilePhoto float-left">
                        <img class="img-thumbnail rounded-circle" src="https://storage.cloud.google.com/gondor-tree/profile/<%=ref.getPhoto()%>" alt="<%=usess.getName()%>" width="200" />
                    </div>
                    <div class="fundoNomeProfile">
                    <% if (ref.getFamilyId() != null) {%>
                    <h3 class="nameProfile"><%=ref.getName()%> <%=ref.getFamilyId().getName()%> </h3>
                    <%
                    } else {
                    %>
                    <h3 class="nameProfile"><%=ref.getName()%></h3>
                    <%
                        }
                    %>
                    </div>
                    <% if (libEdit) {%>
                    <div class="position-relative editButtons float-right">
                        <button class="btn btn-dark" data-toggle="modal" data-target="#editProfile<%=id%>"><i class="fas fa-user-edit"></i> Editar Perfil</button>
                    </div>

                    <!-- Modal -->
                    <div class="modal fade" id="editProfile<%=id%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Perfil</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form class="formInputMember" action="member.htm?action=edit" role="form" method="post" enctype="multipart/form-data">
                                    <div class="modal-body">
                                        <input text="hidden" name="id" id="id" value="<%=ref.getId()%>" required >
                                        <div class="form-group">
                                            <label for="photo">Foto Perfil:</label>
                                            <input id="photo" name="photo" type="file" class="form-control" required />
                                        </div>
                                        <div class="form-group">
                                            <label for="coverPhoto">Foto Fundo: </label>
                                            <input id="coverPhoto" name="coverPhoto" type="file" class="form-control" required />
                                        </div>
                                        <div class="form-group">
                                            <label for="name">Primeiro Nome: </label>
                                            <input text="text" class="form-control mr-sm-2 col-md-12 float-left " name="name" id="name" value="<%=ref.getName()%>" />
                                        </div>
                                        <div class="form-group">
                                            <label for="family">Selecione Família Principal: </label>
                                            <select class="form-control family" name="family.id" id="family"  style="width: 100%;"  >
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label for="description">Descrição: </label>
                                            <textarea class="form-control" name="description" id="description"><%=ref.getDescription()%></textarea>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-primary">Salvar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
                <div class="col-md-12" style="margin-bottom: 30px;">
                    <div class="descriptionProfile mr-sm-2 col-md-4  float-left">
                        <p><%=ref.getDescription()%></p>
                    </div>   
                    <div class="descriptionProfile mr-sm-0 col-md-7 float-left" >
                        <form>
                            <textarea class="form-control" name="testimony" id="testimony" rows="8">Escreve um Depoimento</textarea>
                            <br/>
                            <button type="submit" class="btn btn-primary float-right">Salvar</button>
                        </form>

                    </div>   
                </div>
            </div>
        </div>
    </body>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
    <script src="https://kit.fontawesome.com/ac85440b36.js" crossorigin="anonymous"></script>

    <script type="text/javascript">
        $("[data-hide]").on("click", function () {
            $("." + $(this).attr("data-hide")).hide();
        });
        <% if (failS) { %>
        $(".alert").toggle().addClass('btn-danger');
        $(".alert #resposta").html("Ops!! <br/> Aconteceu erros ao editar!");
        <%
                }
        %>
    </script>
</html>