<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.user == null }"></c:if>

	<div class="container"
		style="position: relative; min-height: 62vh">
	
		<div
			class="row d-flex justify-content-center align-items-center h-100">
			<div class="col-lg-12 col-xl-11">
				<div class="card-body p-md-5">
					<div class="row justify-content-center">
						<div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                          
							<div class="card">
								<div class="card-header text-center">Appointment</div>
								<div class="card-body">

									<sf:form method="post"
										action="${pageContext.request.contextPath}/bookappointment"
										modelAttribute="form">

										<br style="clear: both">
										<%
											String uri = (String) request.getAttribute("uri");
										%>
										<s:bind path="id">
											<sf:input path="${status.expression}" type="hidden" />
										</s:bind>
										
<sf:input type="hidden" id="userid" class="form-control form-control-lg" path="userid" name="userid" value="${userDTO.id}"/>
<sf:input type="hidden" id="serviceid" class="form-control form-control-lg" path="serviceid" name="serviceid" value="${servicesDTO.id}"/>
<sf:input type="hidden" id="productid" class="form-control form-control-lg" path="productid" name="productid" value="${productDTO.id}"/>
										<b class="text-center"><%@include
												file="businessMessage.jsp"%></b>

										<div class="form-outline">
											<font color="red" style="font-size: 13px"><sf:errors
													path="${status.expression}" /></font> <label class="form-label"
												for="form3Example4">Employee Name</label>
											<s:bind path="employeeName">
												<sf:input type="text" path="${status.expression}"
												readonly="true"	placeholder="Enter Employee Name" class="form-control"
													 />
											</s:bind>
										</div>


										<div class="form-outline">
											<label class="form-label" for="form3Example4">User
												Name</label>
											<s:bind path="userName">
												<sf:input type="text" path="${status.expression}"
													placeholder="Enter User Name" class="form-control"
													readonly="true" value="${sessionScope.user.userName}" />
												<font color="red" style="font-size: 13px"><sf:errors
														path="${status.expression}" /></font>
											</s:bind>
										</div>
										
									<div class="form-outline">
											<label class="form-label" for="form3Example4">Price
												</label>
											<s:bind path="price">
												<sf:input type="text" path="${status.expression}"
													class="form-control"
													readonly="true" value="${productDTO.price}" />
												<font color="red" style="font-size: 13px"><sf:errors
														path="${status.expression}" /></font>
											</s:bind>
										</div>


				<div class="row">
                
                <div class="col-md-6 mb-4 d-flex align-items-center">
                  <s:bind path="fromBookingDate">
                  <div class="form-outline datepicker w-100">
                  <label for="fromBookingDate" class="form-label">FromBookingDate</label>
                    <sf:input type="date" class="form-control form-control-lg" id="fromBookingDate" path="fromBookingDate" placeholder="dd/MM/yyyy" min="<%= df.format(new java.util.Date())%>"/>
                     <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </div>
                  </s:bind>
                </div>
                
                
                 <div class="col-md-6 mb-4 d-flex align-items-center">
                  <s:bind path="fromTime">
                  <div class="form-outline datepicker w-100">
                  <label for="fromTime" class="form-label">FromTime</label>
                    <sf:input type="time" class="form-control form-control-lg" id="fromTime" path="fromTime" placeholder="HH:MM:SS"/>
                    <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </div>
                  </s:bind>
                </div>
                
                </div>
              
               <div class="row">
                
                <div class="col-md-6 mb-4 d-flex align-items-center">
                  <s:bind path="toBookingDate">
                  <div class="form-outline datepicker w-100">
                  <label for="toBookingDate" class="form-label">ToBookingDate</label>
                    <sf:input type="date" class="form-control form-control-lg" id="toBookingDate" path="toBookingDate" placeholder="dd/MM/yyyy" min="<%= df.format(new java.util.Date())%>"/>
                    <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  </div>
                  </s:bind>
                </div>
                
                
                 <div class="col-md-6 mb-4 d-flex align-items-center">
                  <s:bind path="toTime">
                  <div class="form-outline datepicker w-100">
                  <label for="toTime" class="form-label">ToTime</label>
                  
                    
                    <sf:input type="time" class="form-control form-control-lg" id="toTime" path="toTime" placeholder="HH:MM:SS" min="00" max="24"/>
                    <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    
                    
                  </div>
                  </s:bind>
                </div>
                
                </div>
 



										<div class="text-center">

											<input type="submit" value="Save" name="operation"
												class="btn btn-primary">
										</div>

									</sf:form>
								</div>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>