<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add member</title>
<style>
.error{color:Red}

td {
    padding: 5px 5px 5px 5px
}

table {
    border: solid lightgray;
    border-radius: 10px;
}

</style>
</head>
<body>

	
	<form:form action="addOrUpdateMember" method="POST" modelAttribute="member" enctype="multipart/form-data">
		<div>
	
		<table> 
		<tr>
			<td style="color:blue; font-style: italic;">General Details:</td>
		</tr>
		<tr style="display:none;">
			<td>
			<form:hidden path="id"/>
			</td>
		</tr>
		
		<tr style="display:none">
		<td>
		<input type='hidden' id='mode' name='mode' value="${action}"/>
		</td>
		</tr>
		
		<tr>
			<td>*Name:</td> 
			<td>
			<form:input path="name" required="required"/>
			</td>
		</tr>
		
		
		<tr>
		  <td>Gender:</td>
		  <td>
		  	  Male<form:radiobutton path="gender" value="Male"/>  
              Female<form:radiobutton path="gender" value="Female"/>
           </td>  
        </tr>
		
		<tr>
			<td>Blood type:</td>
			<td>
			<form:select path="bloodType">  
			 <form:option value="" label=""/>  
	        <form:option value="O +ve" label="O +ve"/>  
	        <form:option value="A +ve" label="A +ve"/>  
	        <form:option value="B +ve" label="B +ve"/>  
	        <form:option value="O -ve" label="O -ve"/> 
	        <form:option value="A -ve" label="A -ve"/>   
	        <form:option value="B -ve" label="B -ve"/>  
	        <form:option value="AB +ve" label="AB +ve"/>  
	        <form:option value="AB -ve" label="AB -ve"/>  
	        </form:select> 
	        </td> 
        </tr>
		
		
		<tr>
			<td>*E-mail:</td>
			<td> 
			<form:input path="email" required="required"/>
			<form:errors path="email" cssClass="error"/>
			</td>
		<tr>
		
		
		<tr>
			<td>Contact number:</td>
			<td>
			<form:input path="contact"/>
			<form:errors path="contact" cssClass="error"/>
			</td>
		</tr>
		
		<tr>
			<td>Emergency Contact number:</td>
			<td>
			<form:input path="emergencyContact"/>
			<form:errors path="emergencyContact" cssClass="error"/>
			</td>
		</tr>
		
		
		<tr>
			<td>Date of Birth:</td>
			<td>
			<form:input type="date" path="dob" placeholder="dd/mm/yyyy"/>
			<form:errors path="dob" cssClass="error"/>
			</td>
		</tr>
		
		<tr>
			<td>Year of joining DGODS:</td>
			<td>
			<form:input path="yearOfJoiningDgods"/>
			<form:errors path="yearOfJoiningDgods" cssClass="error"/>
			</td>
		</tr>
		
		
		<tr>
			<td>Occupation:</td>
			<td>
			<form:select path="occupation">
			<form:option value="IT" label="IT"/>  
	        <form:option value="Business" label="Business"/>  
	        <form:option value="Others" label="Others"/>  
	        </form:select>
	        </td>
        </tr>
		
		
		<tr>
			<td>Company:</td>
			<td>
			<form:input path="company" placeholder="Enter N/A if not applicable"/>
			</td>
		</tr>
		
		
		<tr>
			<td>Dance Specialization:</td>
			<td>
			<form:input path="danceSpecialization" placeholder="(e.g) Freestyle,Krump"/>
			</td>
		</tr>
		
		
		<tr>
			<td style="color:blue; font-style: italic;">Permanent Address Details: </td>
		</tr>
		
		
		<tr>
			<td>Street:</td>
			<td>
			<form:input path="street"/>
			</td>
		</tr>
		
		
		<tr>
			<td>City:</td>
			<td>
			<form:input path="city"/>
			</td>
		</tr>
		
		
		<tr>
			<td>State:</td>
			<td>
			<form:input path="state"/>
			</td>
		</tr>
		
		
		<tr>
			<td>Pincode:</td>
			<td>
			<form:input path="pinCode"/>
			<form:errors path="pinCode" cssClass="error"/>
			</td>
		</tr>
		
		
		<tr>
			<td>Upload a picture:</td>
			<td>
			<input type="file" name="file"/>  
			</td>
			
		</tr>
		
	</table>	
	</div>
	<br>
	<input type="submit" value="Add"/>
	</form:form>
	
</body>
</html>