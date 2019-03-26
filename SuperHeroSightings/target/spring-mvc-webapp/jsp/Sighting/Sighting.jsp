<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<%-- 
    Document   : Sighting
    Created on : Dec 31, 2018, 9:53:22 PM
    Author     : kaminahar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sightings</title>
<!-- CSS -->



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
        
        
        
        
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
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
                
                
                
                
                      
                               
                
           <div id="sightingsTableDiv">                    
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                   <th>Sighting Date</th>
                                    <th>Person</th>
                                    <th>Location</th>
                                </tr>                            
                            </thead>
                            <tbody> 
                                <c:forEach var="currentSightings" items="${improvedSightingList}">
                                    <tr>
                                        <td>
                                    <a href ="goToSightingsDetailsPage?sightingsID=${currentSightings.sightingsID}"><c:out value="${currentSightings.sightingsDate}"/></a>
                                        </td>
                                        <td>
                                    <a href ="goToPersonDetailsPage?personID=${currentSightings.personID}"><c:out value="${currentSightings.person.personName}"/></a>
                                        </td>
                                       <td>
                                    <a href ="goToLocationDetailsPage?locationID=${currentSightings.locationID}"><c:out value="${currentSightings.location.locationName}"/></a>
                                       </td>
                                        <td>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="displayEditSightingForm?sightingsID=${currentSightings.sightingsID}">Edit</a>
                                        </sec:authorize>
                                        </td>
                                        <td>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="removeSighting?sightingsID=${currentPerson.personID}">Delete</a>
                                        </sec:authorize>
                                         </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div> 
          
       
                
                
                  
                
                
                
   
                
                             
  <h3>Report Sighting</h3> 

<div id="reportSightingDiv">                    
    <form class= "myForm" action="${pageContext.request.contextPath}/addSighting" method="post">
        <div class="form-group">
            <label for="sightingsDate">Sighting Date:</label>
            <input type ="date" name="sightingsDate" />                            
        </div>     
   
 <!--superhero or villain drop down -->     
        
  <div class="form-group">
  <label>Select Superhero or Super-villain:</label>
  <select name="selectPerson" >
     <c:forEach var="currentPerson" items="${personList}">
    <option value="${currentPerson.personID}">${currentPerson.personName}</option>
    </c:forEach>
  </select> 
  </div>
       
        
        
     <!-- location drop down-->     
  <div class="form-group">
  <label>Select Location:</label>
  <select name="selectLocation">
     <c:forEach var="currentLocation" items="${locationList}">
    <option value="${currentLocation.locationID}">${currentLocation.locationName}</option>
    </c:forEach>
  </select> 
  </div>      
        
    
        
     <button type="submit" class="btn btn-primary" value="addSighting">Submit</button>    
        
    </form>
</div>



 
 
 
 
 
 
 
 

    </div>     
    </body>
    
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</html>
