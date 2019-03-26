<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Superhero & Super-villain Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/bob">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/personIndex">Persons</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/sightingIndex">Sightings</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerIndex">Superpowers</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/locationIndex">Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/organizationIndex">Organizations</a></li>
                </ul>    
            </div>
                
                
                
                
            <div class="contentTitle">
                <h3>About</h3>
            </div>
            <div class="pageContent">
              <p>In a chaotic world full of super-villains, we are blessed to have superheroes who fight to protect and defend us from harm's way. 
                  As civilians, it's helpful to be aware of who those superheroes and super-villains may be. Here is a website to keep track of them.
                  This website allows us to report superhero and super-villain sightings along with their locations and organizations they are associated
                  with. Enjoy!</p>
            </div>    
             <h3>10 Most Recent Sightings</h3> 
             
                <div id="tenSightingsOnHomePageTableDiv">                                       
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
                                <c:forEach var="currentSightings" items="${lastTenSightingList}">
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
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div> 
                   
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
             




        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>