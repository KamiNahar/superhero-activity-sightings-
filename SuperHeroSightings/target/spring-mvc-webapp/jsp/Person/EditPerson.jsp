<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- 
    Document   : EditPerson
    Created on : Jan 1, 2019, 7:43:56 PM
    Author     : kaminahar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Person</title>

 <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">         
        
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

#EditPersonDiv {
    
}

</style>
                    
        
     
        
        
        
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

                
                
        

 <div align="left"> 
 <h3>Edit Person</h3> 
 <div id="EditPersonDiv"> 
 
             <sf:form  class="form-horizontal" role="form" modelAttribute="getPersonByIdToEdit"
                     action="editPerson" method="POST">
                 
                 
                <div class="form-group">
                    <label for="name" class="col-md-4 control-label">Person Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="personName"
                                  path="personName" placeholder="personName"/>
                        <sf:errors path="personName" cssclass="error"></sf:errors>
                        <sf:hidden path="personID"/>
                   </div>
                </div>
                   
                   
                <div class="form-group">
                    <label for="descrip" class="col-md-4 control-label">Description:</label>
                    <!-- path has to be the same as the field names in your DTO (variable name) -->
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="description"
                                  path="personDescription" placeholder="description"/>
                        <sf:errors path="personDescription" cssclass="error"></sf:errors>
                    </div>
                </div>
                    
                       
                <div class="form-group" >
                    <label for="company" class="col-md-4 control-label">Select a category:</label>                          
                    <div class="col-md-4">
                        <label> Superhero</label>
                    <sf:radiobutton value = "true"  class="form-control" id="true"
                                  path="isHero" placeholder="company"/>
                        <sf:errors path="isHero" cssclass="error"></sf:errors>
                    </div>
                    
                    
                  <div class="col-md-4">
                        <label> Super-Villain</label>
                    <sf:radiobutton value = "false"  class="form-control" id="false"
                                  path="isHero" placeholder="company"/>
                        <sf:errors path="isHero" cssclass="error"></sf:errors>
                    </div>
                </div>
                   
 </div>                  
                  
        
<!--Drop down menus to select superpower and organizations already in the table user wants to edit -->        
        
   <!--superpower drop down -->                              
 <div class="form-group">
  <label>Select superpower:</label>
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
     
        
       
            
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-primary" value="Update Person"/>
                    </div>
                </div>
            </sf:form>  
 </div> 
 
 
 
 
 
 
 
 

 
 
 
        
        
        
    </body>
</div>
        
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</html>
