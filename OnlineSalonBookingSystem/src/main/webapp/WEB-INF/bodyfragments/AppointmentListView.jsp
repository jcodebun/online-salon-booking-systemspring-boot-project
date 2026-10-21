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
	<c:url var="delete" value="/appointment/Delete" />
	<c:url var="confirm" value="/appointment/confirm"/>
	<c:url var="cancle" value="/appointment/cancle"/>
	<c:url var="pay" value="/pay"/>
	
	<h2 align="center">Appointment List</h2>
	<br>
	<div class="container mt-4"
		style="position: relative; min-height: 72vh">

		<sf:form method="post"
			action="${pageContext.request.contextPath}/appointmentList"
			modelAttribute="form">
			
<b class="text-center"><%@ include file="businessMessage.jsp"%></b>
			<table class="table table-striped table table-dark">
				<tr>

					<th scope="col">User Name</th>
					<th scope="col">Employee Name</th>
					<th scope="col">To Time</th>
					<th scope="col">Status</th>
					<c:if test="${sessionScope.user.roleid == 1}">
					<th scope="col">Action</th>
					</c:if>
				</tr>

				<c:forEach items="${list}" var="appointmentlist">

					<tr>

						<td>${appointmentlist.userName}</td>
						<td>${appointmentlist.employeeName}</td>
						<td>${appointmentlist.toTime}</td>
						<td class="text-info font-weight-bold">${appointmentlist.status}</td>
						
						<c:if test="${sessionScope.user.roleid == 1}">
						<td><a	class="btn btn-warning" href="${cancle}?id=${appointmentlist.id}&status=${appointmentlist.status}"><i
								class='fa fa-times' style='font-size: 15px;'></i></a>
								<a class="btn btn-success" href="${confirm}?id=${appointmentlist.id}&status=${appointmentlist.status}"><i
										class='fa fa-check' style='font-size: 15px;'></i></a>
											<a class="btn btn-danger" href="${delete}?id=${appointmentlist.id}"><i
										class='fa fa-trash' style='font-size: 15px;'></i></a>
								</td>
								</c:if>
								<c:if test="${sessionScope.user.roleid == 3}">
						<td><a	class="btn btn-warning" href="${cancle}?id=${appointmentlist.id}&status=${appointmentlist.status}"><i
								class='fa fa-times' style='font-size: 15px;'></i></a>
								<a class="btn btn-success" href="${confirm}?id=${appointmentlist.id}&status=${appointmentlist.status}"><i
										class='fa fa-check' style='font-size: 15px;'></i></a>
									</td>
								</c:if>
									
						</tr>
					
				</c:forEach>
				</tbody>
			</table>


		</sf:form>


	</div>
</body>
</html>