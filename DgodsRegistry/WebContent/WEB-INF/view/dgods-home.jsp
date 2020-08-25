<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dgods Home</title>
<style>
	.heading{background-color: lightgreen;
  font-family: verdana;
  font-size: 100%;
  border: 1px solid #c3c3c3;}
  
    .header{background-color: lightblue;
  font-family: verdana;
  font-size: 100%;}
  
.background{
	
    max-width: 100%;
	display: block; 
    position: absolute;
  	top: 0;
  	bottom: 0;
 	width: 100%;
	background-image:url(${pageContext.request.contextPath}/resources/images/mj4.jpg);
	overflow-x: scroll;
}
.description{
	color:blue; 
	font-style: italic;
}

</style>
</head>
<body>

<div class="background">
	
	<div>
		<h2 style="text-align:center">DGODS Registry</h2>
	</div>
<sec:authorize access="hasRole('ROLE_DGODS_ADMIN')">
	<input type="submit" value="Add member" onclick="window.location.href='showAddMemberPage'"/>
	<br><br>
</sec:authorize>
<c:choose>
	<c:when test="${addMember == 'no'}">
		<script type="text/javascript">
			alert("Member already present!!");
		</script>
	</c:when>
	<c:when test="${addMember == 'yes'}">
		<script type="text/javascript">
			alert("Member added successfully!!");
		</script>
	</c:when>
	<c:when test="${updateMember == 'yes'}">
		<script type="text/javascript">
			alert("Member updated successfully!!");
		</script>
	</c:when>
</c:choose>

<form:form action="viewMember" method="GET">
	
	<div>
		<table>
			<tr>
				<td class="description">
				Provide below details to view a member from registry: 
				</td>
			</tr>
			<tr>
				<td>Name: <input type="text" name="name"/></td>
				<td>Email: <input type="text" name="email"/></td>
				<td><input type="submit" value="View member"/></td>
			</tr>
		</table>
	</div>
		  
	<br><br>

</form:form>
<c:choose>
	<c:when test="${memberNotAvailable == 'yes'}">
		<script type="text/javascript">
			alert("Member not available. Make sure you entered correct details.");
		</script>
	</c:when>
	<c:when test="${memberDeleted == 'yes'}">
		<script type="text/javascript">
			alert("Member removed from registry.");
		</script>
	</c:when>
</c:choose>

<sec:authorize access="hasRole('ROLE_DGODS_ADMIN')">
	<form:form action="deleteMember" method="GET">
	
	<!-- Initially had this method as "POST", due to some reason the post controller method was only identified when the user was
	authorized, i.e. dgodsadmin has the authorization to remove a member but dgodsmember does not.
	The post method worked for dgodsadmin. For dgodsmember it has to be redirected to access-denied page but it didn't happen so.
	Need to investigate more on this. Having it as "GET" method solved the issue. -->
		
		
		<div>
			<table>
				<tr>
					<td class="description">
					Provide below details to remove a member from registry:
					</td>
				</tr>
				<tr>
					<td>Name: <input type="text" name="name"/></td>
					<td>Email: <input type="text" name="email"/></td>
					<td><input type="submit" value="Remove member"/></td>
				</tr>
			</table>
		</div>
		<br><br>
	
	</form:form>
</sec:authorize>

<input type="submit" value="Show all members" onclick="window.location.href='showAllMembers'"/>

<br><br>
	
	<c:if test="${fn:length(members) > 0}">
	
	<table style="width:80%">
		
		<tr class="header" style="text-align:center">
			<th>Name</th>
			<th>Action</th>
		</tr>
		
		<c:forEach var="data" items="${members}">
		
		<c:url var="viewLink" value="/dgods/viewMember">
				<c:param name="name" value="${data.name}"></c:param>
				<c:param name="email" value="${data.email}"></c:param>
		</c:url>
		
		<tr style="text-align:center">
			<td>${data.name}</td>
			<td><a href="${viewLink}">View</a>
		</tr>
		<tr></tr>
		<tr></tr>
		
	</c:forEach>
		
	</table>
	
	</c:if>
	
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
        	
        	<input type="submit" value="Logout"/>
        	
    </form:form>

</div>			
</body>
</html>