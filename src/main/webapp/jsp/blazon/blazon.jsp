<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.gondortree.model.*"%>
<%
    Member usess = (Member) session.getAttribute("user");  
%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>

    <head>
        <title>GondorTree | Registre sua Familia</title>
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">    
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"> 
    </head>
    <%@ include file="/includes/navbar.jsp" %>
    <body>
        <div class="container">
            <div class="row">
                <form action="blazon.htm?action=register" class="col-md-8 alignCenter" role="form" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="create.id" id="createId" value="<%=usess.getId() %>" required />
                    <input name="name" class="form-control mr-sm-2 col-md-6 float-left " type="text" placeholder="Nome da Familia" required />
                    <input name="image" type="file" class="form-control mr-sm-2 col-md-4 float-left" required />
                    <button class="btn btn-outline-info my-2 my-sm-0 " type="submit">Criar</button>
                </form>
            </div>
            <br/>
            <hr/>
            <div class="listFamily">
                <h3 class="text-left text-danger">Familias já criadas</h3>
                <div class="row">
                    <form class="col-md-6">
                        <input class="form-control mr-sm-4 col-md-5 float-left " type="text" placeholder="Procurar por Nome da Familia">
                        <button class="btn btn-outline-info my-2 my-sm-0 " type="submit">Procurar</button>
                    </form>
                </div>

                <div class="alignTopper col-md-10 alignCenter" style="margin-top: 15px;">
                    <%
                        List<Blazon> l = (List<Blazon>) request.getAttribute("list");
                        for(Blazon ref : l){
                    %>
                    
                    <div class="card float-left col-md-3 mr-sm-2 alignTopper" style="cursor: pointer;" data-toggle="modal" data-target="#inFamily<%=ref.getId() %>">
                        <img class="card-img-top" src="https://storage.cloud.google.com/gondor-tree/blazon/<%=ref.getImage()%>" alt="<%=ref.getName() %>" height="150">
                        <div class="card-body">
                            <h5 class="card-title"><%=ref.getName() %></h5>
                            <p class="card-text text-success"><span class="text-default">Criado por: </span><%=ref.getCreateId().getName() %></p>
                        </div>
                    </div>
                        
                    <!-- Modal -->
                    <div class="modal fade" id="inFamily<%=ref.getId() %>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Família <%=ref.getName() %></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p class="text-info">É membro dessa família? Solicite para entrar!</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                     <form action="blazon.htm?action=infamily" role="form" method="post" >
                                        <input type="hidden" id="memberId" name="member.id" value="<%=usess.getId()%>" />
                                        <input type="hidden" id="familyId" name="family.id" value="<%=ref.getId()%>" />
                                        <% if(usess.getId() == ref.getCreateId().getId() ) { %>
                                        <input type="hidden" id="membersFunction" name="membersFunction" value="1" />
                                        <% 
                                            } else {
                                        %>
                                        <input type="hidden" id="membersFunction" name="membersFunction" value="2" />
                                        <% 
                                            }
                                        %>
                                        <button type="submit" class="btn btn-primary">Solicitar Entrada</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                        
                    <% 
                        }
                    %>
                        
                </div>
            </div>
        </div>
    </body>   
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"> </script>
    <script src="https://kit.fontawesome.com/ac85440b36.js" crossorigin="anonymous"></script>
</html>