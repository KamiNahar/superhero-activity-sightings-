<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%-- 
    Document   : Organization
    Created on : Dec 31, 2018, 9:53:50 PM
    Author     : kaminahar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Organizations</title>
        
<style>
            
.myForm label {
text-align: left;
display: block;
}  

.myForm input,
.myForm date,
.myForm textArea,
.myForm radio {
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
        <h1>Organizations</h1>
        <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation"><a href="${pageContext.request.contextPath}/bob">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/personIndex">Persons</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/sightingIndex">Sightings</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/superpowerIndex">Superpowers</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/locationIndex">Locations</a></li>
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/organizationIndex">Organizations</a></li>
                </ul>    
            </div>
                
                
    <!--Log out option -->
    <c:if test="${pageContext.request.userPrincipal.name != null}">
    <p>Hello : ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </p>
    </c:if> 
                
                
                
                <div id="orgTableDiv">                    
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Organization Name</th>
                                    <th>Contact Info</th>
                                    <th>Organization Description</th>
                                    <th>Hero</th>
                                </tr>                            
                            </thead>
                            <tbody> 
                                <c:forEach var="currentOrg" items="${orgList}">
                                    <tr>
                                        <td> 
                                         <a href ="goToOrganizationsDetailsPage?organizationID=${currentOrg.organizationID}"><c:out value="${currentOrg.orgName}"/></a>
                                        </td>
                                        <td>
                                        <c:out value="${currentOrg.orgContactInfo}"/>
                                        </td>
                                       <td>
                                         <c:out value="${currentOrg.orgDescription}"/>
                                        </td>
                                        <td>
                                        <c:out value="${currentOrg.isHeroOrg}"/>
                                        </td>
                                        <td>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                         <a href="displayEditOrganizationForm?organizationID=${currentOrg.organizationID}">Edit</a>
                                        </sec:authorize>
                                        </td>
                                        <td>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="removeOrg?organizationID=${currentOrg.organizationID}">Delete</a>
                                        </sec:authorize>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
             
                
                
                
                
<sec:authorize access="hasRole('ROLE_ADMIN')">
                <h3>Add Organization </h3>
                <div id="addLorganizationDiv">  
                    <form class= "myForm" action="${pageContext.request.contextPath}/addOrganization" method="post">
                        <div class="form-group">
                            <label for="orgName">Organization Name:</label>
                            <input name="orgName" type="text" maxlength="50"/>                            
                        </div>                        
                        <div class="form-group">
                            <label for="orgContactInfo">Contact Info:</label>
                            <input name="orgContactInfo" type="text" maxlength="100"/>
                        </div>                                                
                        <div class="form-group">
                            <label for="orgDescription">Organization Description:</label>
                            <input name="orgDescription" type="text" maxlength="50"/>                            
                        </div>         
                        <div class="form-group">
                            <label for="isHeroOrg">Category:</label>
                            <label class="choice" > <input type="radio" name="isHero"  value="true"> Superhero </label>
                            <label class="choice" > <input type="radio" name="isHero" value="false"> Super-villain </label>
                        </div>  
                        
 <!-- organization members drop down-->     
  <div class="form-group">
  <label>Select organization member:</label>
  <select name="selectOrgMember" multiple="true">
     <c:forEach var="currentPerson" items="${personList}">
    <option value="${currentPerson.personID}">${currentPerson.personName}</option>
    </c:forEach>
  </select> 
  </div>  
                                 
                        
                        <button type="submit" class="btn btn-primary" value="addOrganization">Submit</button> 
                    </form>
                </div>
</sec:authorize>
                        
  
                        
                        
                        
   </div>
    </body>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</html>


