<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<br>
	<c:url var="delete" value="/emp/Delete" />
	<c:url var="appoientment" value="/emp/appointment"/>
	
	<h2 align="center">Employee List</h2>
	<br>
	<div class="container mt-4"
		style="position: relative; min-height: 72vh">
		
         <h3>Selected Service is : ${servicesDTO.serviceName}  and Selected Products is: ${productDTO.productName}</h3>
		<sf:form method="post"
			action="${pageContext.request.contextPath}/employeeList"
			modelAttribute="form">
			
			
			<table width="100%">
				<tr>
					<td align="center"><s:bind path="email">
							<sf:input path="${status.expression}" type="text" name="name"
								placeholder="Search By Email" value="" />
						</s:bind> &emsp;&emsp; <input type="submit" name="operation" value="Search">&nbsp;&nbsp;&nbsp;<input
						type="submit" name="operation" value="Reset"></td>
				</tr>
			</table>
			<br>
<b class="text-center"><%@ include file="businessMessage.jsp"%></b>
			<table class="table table-striped table table-dark">
				<tr>

					<th scope="col">Employee Name</th>
					<th scope="col">Email</th>
					<th scope="col">PhoneNo</th>
					<th scope="col">Gender</th>
					<th scope="col">DOB</th>
					<th scope="col">Address</th>
					<th scope="col">Action</th>
				</tr>

				<c:forEach items="${list}" var="userlist">

					<tr>

						<td>${userlist.userName}</td>
						<td>${userlist.email}</td>
						<td>${userlist.phoneNo}</td>
						<td>${userlist.gender}</td>
						<td><fmt:formatDate type="both" dateStyle="short" timeStyle="short"  value="${userlist.dob}" /></td>  
						<td>${userlist.address}</td>

						<c:if test="${sessionScope.user.roleid == 1}">
								<td>
									<a class="btn btn-danger" href="${delete}?id=${userlist.id}"><i
										class='	far fa-trash-alt' style='font-size: 15px;'></i></a></td>
							</c:if>
							<c:if test="${sessionScope.user.roleid == 2}">
							<td><a class="btn btn-info" href="${appoientment}?user_id=${userlist.id}&employeeName=${userlist.userName}&service_id=${servicesDTO.id}&product_id=${productDTO.id}">Take Appointment</a></td>
										</c:if>

					</tr>
				</c:forEach>
				</tbody>
			</table>


		</sf:form>


	</div>
</body>
</html>