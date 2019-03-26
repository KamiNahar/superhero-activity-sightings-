<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%-- 
    Document   : Location
    Created on : Dec 31, 2018, 9:53:01 PM
    Author     : kaminahar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <title>Locations</title>

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
        
        <h1>Locations</h1>
        <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation"><a href="${pageContext.request.contextPath}/bob">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/personIndex">Persons</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/sightingIndex">Sightings</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerIndex">Superpowers</a></li>
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/locationIndex">Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/organizationIndex">Organizations</a></li>
                </ul>    
            </div>
            
    <!--Log out option -->            
    <c:if test="${pageContext.request.userPrincipal.name != null}">
    <p>Hello : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </p>
    </c:if>            
                

                <div id="locationTableDiv">                    
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Location Name</th>
                                    <th>Description</th>
                                    <th>Address</th>
                                    <th>Latitude</th>
                                    <th>Longitude</th>
                                    <th></th>
                                    <th></th>
                                </tr>                            
                            </thead>
                            <tbody>
                                <c:forEach var="currentLocation" items="${locationList}">
                                    <tr>
                                        <td> 
                                        <a href ="goToLocationDetailsPage?locationID=${currentLocation.locationID}"><c:out value="${currentLocation.locationName}"/></a>                        
                                        </td>
                                        <td>
                                        <c:out value="${currentLocation.locationDescription}"/>
                                        </td>
                                        <td>
                                        <c:out value="${currentLocation.locationAddress}"/>
                                        </td>
                                        <td>
                                        <c:out value="${currentLocation.locationLongitude}"/>
                                        </td>
                                        <td>
                                        <c:out value="${currentLocation.locationLatitude}"/>
                                        </td>
                                        <td>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                         <a href="displayEditLocationForm?locationID=${currentLocation.locationID}">Edit</a>
                                        </sec:authorize>
                                        </td>
                                        <td>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="removeLocation?locationID=${currentLocation.locationID}">Delete</a>
                                        </sec:authorize>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                
                
                
                
                
                
  
                <h3>Add Location </h3>
                <div id="addLocationDiv">  
                    <form class= "myForm" action="${pageContext.request.contextPath}/addLocation" method="post">
                        <div class="form-group">
                            <label for="locationName">Location Name:</label>
                            <input name="locationName" type="text" maxlength="50"/>                            
                        </div>                        
                        <div class="form-group">
                            <label for="locationDescription">Description:</label>
                            <input name="locationDescription" type="text" maxlength="100"/>
                        </div>                                                
                        <div class="form-group">
                            <label for="locationAddress">Address:</label>
                            <input name="locationAddress" type="text" maxlength="50"/>                            
                        </div>         
                        <div class="form-group">
                            <label for="locationLatitude">Latitude:</label>
                            <input name="locationLatitude" type="text"/>                            
                        </div>     
                        <div class="form-group">
                            <label for="locationLongitude">Longitude:</label>
                            <input name="locationLongitude" type="text"/>                            
                        </div>     
                        <button type="submit" class="btn btn-primary" value="addLocation">Submit</button> 
                    </form>
                </div>                
        
                        
                        
                        
                        
                        
                        
        </div>
    </body>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</html>
