$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});


// CLIENTMODEL=========================================================================
function validateAppointmentForm()
{
// CODE
//PatientName
if ($("#PatientName").val().trim() == "")
 {
 return "Insert Patient Name.";
 }
//DoctorName
if ($("#DoctorName").val().trim() == "")
{
return "Insert Doctor Name.";
}
//HosptlName
if ($("#HosptlName").val().trim() == "")
{
return "Insert Hosptl Name.";
}

// email

function isEmail(email) {
	var regex =  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/; 
		return regex.test(email);
}


if ($("#email").val().trim() == "")
{
return "Insert Email.";
}
else if(!isEmail($("#email").val())){
	return "Insert valid Email";
}



// date-------------------------------
if ($("#date").val().trim() == "")
 {
 return "Insert Date.";
 }
//Time
if ($("#time").val().trim() == "")
 {
 return "Insert Time.";
 }

// Charge
if ($("#charge").val().trim() == "")
{
return "Insert Charge.";
}
//is numerical value
var tmpCharge = $("#charge").val().trim();
if (!$.isNumeric(tmpCharge))
{
return "Insert a numerical value for  Charge.";
}
$("#charge").val(parseFloat(tmpCharge).toFixed(6));

return true;
}



//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidAppointmentIdSave").val($(this).closest("tr").find('#hidAppointmentIdUpdate').val());
 $("#PatientName").val($(this).closest("tr").find('td:eq(0)').text());
 $("#DoctorName").val($(this).closest("tr").find('td:eq(1)').text());
 $("#HosptlName").val($(this).closest("tr").find('td:eq(2)').text());
 $("#email").val($(this).closest("tr").find('td:eq(3)').text());
 $("#date").val($(this).closest("tr").find('td:eq(4)').text());
 $("#time").val($(this).closest("tr").find('td:eq(5)').text());
 $("#charge").val($(this).closest("tr").find('td:eq(6)').text());
 
});


//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateAppointmentForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }

var type = ($("#hidAppointmentIdSave").val() == "") ? "POST" : "PUT";


$.ajax(
		{
		 url : "AppointmentAPI",
		 type : type,
		 data : $("#formAppointment").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onItemSaveComplete(response.responseText, status);
		 }
		});

});
function onItemSaveComplete(response, status)
{
if (status == "success")
 {
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		
		$("#divItemsGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while saving.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while saving..");
 		$("#alertError").show();
 	}
		$("#hidAppointmentIdSave").val("");
		$("#formAppointment")[0].reset();
}



$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "AppointmentAPI",
		 type : "DELETE",
		 data : "AppointmentId=" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onItemDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onItemDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

