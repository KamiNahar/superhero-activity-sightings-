<%-- 
    Document   : addUserForm
    Created on : Jan 24, 2019, 4:34:56 PM
    Author     : kaminahar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Superhero and Super-Villain Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Superhero and Super-Villain Sightings</h1>
            <hr/>
            
            
            
             <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/bob">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/personIndex">Persons</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/sightingIndex">Sightings</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerIndex">Superpowers</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/locationIndex">Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/organizationIndex">Organizations</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayUserList">User Admin</a></li>                        
                    </sec:authorize>
                </ul>    
            </div>           
            
                            
                            
                            
                            
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>
            <h1>Add User Form</h1>
            <form method="POST" action="addUser">
                Username: <input type="text" 
                                 name="username"/><br/>
                Password: <input type="password" 
                                 name="password"/><br/>
                Admin User? <input type="checkbox" 
name="isAdmin" value="yes"/> <br/>
                <input type="submit" value="Add User"/><br/>
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>