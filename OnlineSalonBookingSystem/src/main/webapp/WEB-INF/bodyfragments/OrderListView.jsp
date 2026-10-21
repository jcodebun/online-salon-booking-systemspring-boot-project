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
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<title>Insert title here</title>
</head>
<body>
	<br>
	<c:url var="delete" value="/orders/Delete"/>
	<c:url var="confirm" value="/order/confirm"/>
	<c:url var="cancle" value="/order/cancle"/>
	<c:url var="pay" value="/pay"/>
	
	
	<h2 align="center">Order List</h2>
	<br>
	<div class="ml-4 mr-4 mt-4"
		style="position: relative; min-height: 72vh">

		<sf:form method="post"
			action="${pageContext.request.contextPath}/orderlist"
			modelAttribute="form">
			
			
<b class="text-center"><%@ include file="businessMessage.jsp"%></b>
			<table class="table table-striped table table-dark">
				<tr>

					<th scope="col">User Name</th>
					<th scope="col">Product Name</th>
					<th scope="col">Price</th>
					<!-- <th scope="col">Total Price</th> -->
					<c:if test="${sessionScope.user.roleid == 1}">
					<th scope="col">Action</th>
					</c:if>
					<c:if test="${sessionScope.user.roleid == 2}">
					<th scope="col"></th>
					</c:if>
				</tr>
<c:set var="totalp" value="${0}" />
				<c:forEach items="${list}" var="orderlist">
<c:set var="totalp" value="${totalp +orderlist.servicecharge}" />
					<tr>

						<td>${orderlist.userName}</td>
						<td>${orderlist.productname}</td>
						<td>&#8377; ${orderlist.servicecharge}</td>
					<%-- 	<td>&#8377; ${totalp}</td> --%>
					
					<c:if test="${sessionScope.user.roleid == 1}">
						<td>
											<a class="btn btn-danger" href="${delete}?id=${orderlist.id}"><i
										class='fa fa-trash' style='font-size: 15px;'></i></a>
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