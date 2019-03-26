<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%-- 
    Document   : Person
    Created on : Dec 31, 2018, 9:53:13 PM
    Author     : kaminahar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Persons</title>
        
<style>
            
.myForm label 
 {
text-align: left;
display: block;
}  

.myForm input,
.myForm date,
.myForm textArea {
margin-left: 2em;
float:start;
width: 20em;
border: 1px solid #ccc;
padding: 0.5em;
}

</style>
        
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
    </head>
    <body>
    <div class="container">
        <h1>Persons</h1>
        <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation"><a href="${pageContext.request.contextPath}/bob">Home</a></li>
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/personIndex">Persons</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/sightingIndex">Sightings</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerIndex">Superpowers</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/locationIndex">Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/organizationIndex">Organizations</a></li>
                </ul>    
            </div>
                
   <!--Log out option -->
    <c:if test="${pageContext.request.userPrincipal.name != null}">
    <p>Hello : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </p>
    </c:if>     
                
                
                
                 <div id="personTableDiv">                    
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Person Name</th>
                                    <th>Description</th>
                                    <th>Hero</th>
                                </tr>                            
                            </thead>
                            <tbody> 
                                <c:forEach var="currentPerson" items="${personList}">
                                    <tr>
                                        <td>
                                        <c:out value="${currentPerson.personName}"/>
                                        </td>
                                        <td>
                                        <c:out value="${currentPerson.personDescription}"/>
                                        </td>
                                       <td>
                                         <c:out value="${currentPerson.isHero}"/>
                                        </td>
                                        <td>
                                         <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="displayEditPersonForm?personID=${currentPerson.personID}">Edit</a>
                                        </sec:authorize>
                                         </td>
                                        <td>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="removePerson?personID=${currentPerson.personID}">Delete</a>
                                        </sec:authorize>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>               
                
                
                
                
                
<sec:authorize access="hasRole('ROLE_ADMIN')">                
<h3>Add Superhero/Super-Villain</h3> 

<div id="addPersonDiv">                    
    <form class= "myForm" action="${pageContext.request.contextPath}/addPerson" method="post">
        <div class="form-group">
            <label for="name">Person Name:</label>
            <input type ="text" name="personName" />                            
        </div>     
        <div class="form-group">
            <label for="personDescrip">Description:</label>
            <input type ="textArea" rows = "5" name="personDescription" maxlength="100"/>
        </div>                                                
        <div class="form-group">
            <label for="isHeroRadioButton">Category:</label>
            <label class="choice" > <input type="radio" name="isHero"  value="true"> Superhero </label>
            <label class="choice" > <input type="radio" name="isHero" value="false"> Super-villain </label>
        </div> 
        
        
        
   <!--superpower drop down -->     
        
  <div class="form-group">
  <label>Select superpowers:</label>
  <select name="selectSuperpower" multiple="true">
     <c:forEach var="currentSuperpower" items="${superpowerList}">
    <option value="${currentSuperpower.superpowerID}">${currentSuperpower.superpowerName}</option>
    </c:forEach>
  </select> 
  </div>
  
   

   
   <!-- organization drop down-->     
  <div class="form-group">
  <label>Select organization:</label>
  <select name="selectOrganization" multiple="true">
     <c:forEach var="currentOrganization" items="${orgList}">
    <option value="${currentOrganization.organizationID}">${currentOrganization.orgName}</option>
    </c:forEach>
  </select> 
  </div>
        
     
 
    <button type="submit" class="btn btn-primary" value="addPerson">Submit</button>
        
    </form>
</div>
</sec:authorize>           

         
    </div>
    </body>

    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</html>
