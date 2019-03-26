<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- 
    Document   : EditOrgainzation
    Created on : Jan 1, 2019, 7:44:11 PM
    Author     : kaminahar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
        <title>Edit Organization</title>
   
        
        
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
                
               
                
        <h3>Edit Organization</h3>

          <div id="editLorganizationDiv">  

             <sf:form class="form-horizontal" role="form" modelAttribute="getOrganizationByIdToEdit"
                     action="editOrganization" method="POST">
                 
                 
                <div class="form-group">
                    <label for="orgName" class="col-md-4 control-label">Organization Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="orgName"
                                  path="orgName" placeholder="orgName"/>
                        <sf:errors path="orgName" cssclass="error"></sf:errors>
                        <sf:hidden path="organizationID"/>
                   </div>
                </div>
                   
                   
                <div class="form-group">
                    <label for="contact" class="col-md-4 control-label">Contact Info:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="orgContactInfo"
                                  path="orgContactInfo" placeholder="orgContactInfo"/>
                        <sf:errors path="orgContactInfo" cssclass="error"></sf:errors>
                    </div>
                </div>
                    
                    
                <div class="form-group">
                    <label for="orgDescrip" class="col-md-4 control-label">Organization Description:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="orgDescription"
                                  path="orgDescription" placeholder="orgDescription"/>
                        <sf:errors path="orgDescription" cssclass="error"></sf:errors>
                    </div>
                </div>
                    
                <!-- radio buttons -->
                <div class="form-group">
                    <label for="company" class="col-md-4 control-label">Select a category:</label>                          
                    <div class="col-md-4">
                        <label> Superhero</label>
                    <sf:radiobutton value = "true"  class="form-control" id="true"
                                  path="isHeroOrg" placeholder="company"/>
                        <sf:errors path="isHeroOrg" cssclass="error"></sf:errors>
                    </div>
                    
                    
                  <div class="col-md-4">
                        <label> Super-Villain</label>
                    <sf:radiobutton value = "false"  class="form-control" id="false"
                                  path="isHeroOrg" placeholder="company"/>
                        <sf:errors path="isHeroOrg" cssclass="error"></sf:errors>
                    </div>
                </div>    
            
  
                    
                    
  <!-- organization  drop down-->     
  <div class="form-group">
  <label>Select organization member:</label>
  <select name="selectOrgMember" multiple="true">
     <c:forEach var="currentPerson" items="${personList}">
    <option value="${currentPerson.personID}">${currentPerson.personName}</option>
    </c:forEach>
  </select> 
  </div>       
                    
                    
                    
                      
  
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-primary" value="Update Organization"/>
                    </div>
                </div>
            </sf:form>  
 </div> 
                           
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
        
        
        
        
        
        
        </div>
    </body>
    
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</html>
