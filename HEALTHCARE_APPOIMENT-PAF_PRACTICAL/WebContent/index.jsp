<%@page import="com.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery.min.js"></script>
<script src="Components/Appointment.js"></script>
<script src="Components/jquery.min.js"></script>
<link rel="stylesheet" href="Views/bootstrap.min.css">

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="row">
<div class="col-lg-12">
<div class="card m-b-32">
<div class="card-body">

<form id="formAppointment" name="formAppointment" method="post" action="index.jsp">
Patient Name:
<input id="PatientName" name="PatientName" type="text"
 class="form-control form-control-sm">
<br>
	<label> Doctor Name</label> 
							<select id="DoctorName" name="DoctorName" class="form-control form-control-sm">
							<option value="DR Sajith Mayantha">DR Sajith Mayantha</option>
							<option value="DR Kavishka Herath">DR Kavishka Herath</option>
							<option value="DR Dilakshika Perera">DR Dilakshika Perera</option>
							<option value="DR Chathura Gamage">DR Chathura Gamage</option>
							<option value="DR Anura Bandara">DR Anura Bandara</option>
							</select>
<br>
	<label> Hospital Name</label> 
							<select id="HosptlName" name="HosptlName" class="form-control form-control-sm">
							<option value="Asiri Hospital">Asiri Hospital</option>
							<option value="Lanka Hospitals">Lanka Hospitals</option>
							<option value="Nawaloka Hospitals Limited">Nawaloka Hospitals Limited</option>
							<option value="Hemas Hospital, Wattala">Hemas Hospital,Wattala</option>
							<option value="Ceymed Health care">Ceymed Health care</option>
							<option value="Golden Key Eye and ENT Hospital">Golden Key Eye and ENT Hospital</option>
								</select>
<br> Phone No:
<input id="phone" name="phone" type="text"
 class="form-control form-control-sm">
<br> Email:
<input id="email" name="email" type="text"
 class="form-control form-control-sm">
<br>Date:
<input id="date" name="date" type="date"
 class="form-control form-control-sm">
<br>Time:
<input id="time" name="time" type="time"
 class="form-control form-control-sm">
<br>Charge
<input id="charge" name="charge" type="text"
 class="form-control form-control-sm">
 <br>
<input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
<input type="hidden" id="hidAppointmentIdSave" name="hidAppointmentIdSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<br>
<div id="divItemsGrid">
<%
	Appointment appoObj = new Appointment();
	out.print(appoObj.viewAppointment());
%>
 </div>
 </div>
</div>
</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>