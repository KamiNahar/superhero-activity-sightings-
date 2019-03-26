<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- 
    Document   : EditSighting
    Created on : Jan 1, 2019, 7:43:37 PM
    Author     : kaminahar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Sighting</title>
        
         <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <style>
            
.myForm label {
text-align: left;
display: block;
}  

.myForm input,
.myForm date {
margin-left: 2em;
float:start;
width: 20em;
border: 1px solid #ccc;
padding: 0.5em;
}
        </style>        
        
              
        
    </head>
    <body>
        <div class="container">
        <h1>Sightings</h1>
        <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation"><a href="${pageContext.request.contextPath}/bob">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/personIndex">Persons</a></li>
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sightingIndex">Sightings</a></li>
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
        


 
 
 <h3>Edit Sighting</h3> 
 <div id="editSightingDiv"> 
 
             <sf:form class="form-horizontal" role="form" modelAttribute="getSightingByIdToEdit"
                     action="editSighting" method="POST">
                 
                 
                <div class="form-group">
                    <label for="name" class="col-md-4 control-label">Sighting Date:</label>
                    <div class="col-md-8">
                        <sf:input type="date" class="form-control" id="sightingsDate"
                                  path="sightingsDate" placeholder="sightingsDate"/>
                        <sf:errors path="sightingsDate" cssclass="error"></sf:errors>
                        <sf:hidden path="sightingsID"/>
                   </div>
                </div>
                   
                   
 <!--superhero or villain drop down -->     
        
  <div class="form-group">
  <label class="col-md-4 control-label">Select Superhero or Super-villain:</label>
  <select name="selectPerson" class="col-md-8" >
     <c:forEach var="currentPerson" items="${prsnList}">
    <option value="${currentPerson.personID}">${currentPerson.personName}</option>
    </c:forEach>
  </select> 
  </div>
       
        
        
     <!-- location drop down-->     
  <div class="form-group">
  <label class="col-md-4 control-label">Select Location:</label>
  <select name="selectLocation" class="col-md-8">
     <c:forEach var="currentLocation" items="${locList}">
    <option value="${currentLocation.locationID}">${currentLocation.locationName}</option>
    </c:forEach>
  </select> 
  </div>      
        
         
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-primary" value="Update Sighting"/>
                    </div>
                </div>
            </sf:form>  
 </div> 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
        
        
        
        </div>
    </body>
    
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</html>
