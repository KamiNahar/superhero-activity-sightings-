<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
        <title>Location Details</title>

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
        
        <h1>Location Details</h1>
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
            
                
                <h3 class = "h3"> Location Details </h3>    

                <div id="locationDetailsTableDiv">                    
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Location Name</th>
                                    <th>Description</th>
                                    <th>Address</th>
                                    <th>Latitude</th>
                                    <th>Longitude</th>
                                </tr>
                            </thead>
                            <tbody>
                      
                                    <tr>
                                        <td>
                                        <c:out value="${locationDetails.locationName}"/>
                                        </td>
                                        <td>
                                        <c:out value="${locationDetails.locationDescription}"/>
                                        </td>
                                        <td>
                                        <c:out value="${locationDetails.locationAddress}"/>
                                        </td>
                                        <td>
                                        <c:out value="${locationDetails.locationLongitude}"/>
                                        </td>
                                        <td>
                                        <c:out value="${locationDetails.locationLatitude}"/>
                                        </td>
                                    </tr>
                                <tr>  
                                    </tbody>
                               </table>
                                        
                                        
                           <h3 class="h3"> All Sightings and Persons for Location: </h3>                   
                             <table class="table table-bordered table-hover">   
                            <thead>
                                <tr>
                                    <th>Sighting Date</th>
                                    <th>Person Name</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="currentSighting" items="${sightingDetails}">        
                                    <tr>
                                    <td>
                                    <a href ="goToSightingsDetailsPage?sightingsID=${currentSighting.sightingsID}"><c:out value="${currentSighting.sightingsDate}"/></a>
                                    </td>
                                    <td>
                                    <a href ="goToPersonDetailsPage?personID=${currentSighting.person.personID}"><c:out value="${currentSighting.person.personName}"/></a>
                                    </td>
                                    </tr> 
                                </c:forEach>
                                    
                                    
                           
                            </tbody>    
                        </table>
                    </div>
                </div>
                
                

                        
                        
                        
                        
        </div>
    </body>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</html>
