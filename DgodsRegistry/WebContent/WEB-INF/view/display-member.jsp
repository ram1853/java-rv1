<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member details</title>
<style>
.content{background-color: lightyellow;
  font-family: verdana;
  font-size: 100%;
  max-width: 100%;
  max-height: 100%;
  display: block; 
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;
  }
  
  .position{position:absolute;
  top:50px;
  right:200px; 
  z-index:999;
  border:5px solid black;
  }
  td {
    padding: 5px 5px 5px 5px
}

table {
    border: solid lightgray;
    border-radius: 10px;
}
.description{
	color:blue; 
	font-style: italic;
}
</style>
</head>
<body>
	
	<div class="content">
		
		<table>
			<tr>
				<td style="color:green">General Details:</td>
			</tr>
			<tr>
				<td class="description">Name</td>
				<td>${member.name}</td>
			</tr>
			<tr>
				<td class="description">Gender</td>
				<td>${member.gender}</td>
			</tr>
			<tr>
				<td class="description">Blood type</td>
				<td>${member.bloodType}</td>
			</tr>
			<tr>
				<td class="description">Email</td>
				<td>${member.email}</td>
			</tr>
			<tr>
				<td class="description">Contact</td>
				<td>${member.contact}</td>
			</tr>
			<tr>
				<td class="description">Emergency Contact</td>
				<td>${member.emergencyContact}</td>
			</tr>
			<tr>
				<td class="description">Date of Birth</td>
				<td>${member.dob}</td>
			</tr>
			<tr>
				<td class="description">Year of joining DGODS</td>
				<td>${member.yearOfJoiningDgods}</td>
			</tr>
			<tr>
				<td class="description">Occupation</td>
				<td>${member.occupation}</td>
			</tr>
			<tr>
				<td class="description">Company</td>
				<td>${member.company}</td>
			</tr>
			<tr>
				<td class="description">Dance Specialization</td>
				<td>${member.danceSpecialization}</td>
			</tr>
			<tr>
				<td style="color:green">Residential Details:</td>
			</tr>
			<tr>
				<td class="description">Street</td>
				<td>${member.street}</td>
			</tr>
			<tr>
				<td class="description">City</td>
				<td>${member.city}</td>
			</tr>
			<tr>
				<td class="description">State</td>
				<td>${member.state}</td>
			</tr>
			<tr>
				<td class="description">Pin Code</td>
				<td>${member.pinCode}</td>
			</tr>
			
		</table>
		
		<img src="data:image/jpg;base64,${base64image}" width="240" height="300" class="position"/>
		
		<c:url var="updateLink" value="/dgods/showUpdateMemberPage">
			<c:param name="memberId" value="${member.id}"></c:param>
		</c:url>
	
	<br>
	<sec:authorize access="hasRole('ROLE_DGODS_ADMIN')">
		<a href="${updateLink}">Update</a>
	</sec:authorize>
	</div>
	
	<br><br>
	
	
		
		
</body>
</html>