package com;

import java.sql.*;

public class Appointment {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/helthcare", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String viewAppointment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table  class='table table-hover'><tr><th>Patient Name</th><th>Doctor Name</th><th>Hosptl Name</th><th>Phone No</th>"
					+ "<th>Email</th><th>Date</th><th>Time</th><th>Charge</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from appointment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String AppointmentId = Integer.toString(rs.getInt("AppointmentId"));
				String PatientName  = rs.getString("PatientName");
				String DoctorName  = rs.getString("DoctorName");
				String HosptlName  = rs.getString("HosptlName");
				String phone =  rs.getString("phone");
				String email = rs.getString("email");
				String date  = rs.getString("date");
				String time  = rs.getString("time");
				String charge  = Float.toString(rs.getFloat("charge"));
				// Add into the html table
				output += "<tr><td><input id='hidAppointmentIdUpdate' name='hidAppointmentIdUpdate' type='hidden' value='" + AppointmentId
						+ "'>" + PatientName + "</td>";
				output += "<td>" + DoctorName + "</td>";
				output += "<td>" + HosptlName + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + charge + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
						+ AppointmentId + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Appointments.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertAppointment(String PatientName, String DoctorName, String HosptlName, String phone,
			String email, String date, String time, String charge) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into appointment(`AppointmentId`,`PatientName`,`DoctorName`,`HosptlName`,`phone`,`email`,`date`,`time`,`charge`)"
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, PatientName);
			preparedStmt.setString(3, DoctorName);
			preparedStmt.setString(4, HosptlName);
			preparedStmt.setString(5, phone);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, date);
			preparedStmt.setString(8, time);
			preparedStmt.setFloat(9, Float.parseFloat(charge));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newAppo = viewAppointment();
			output = "{\"status\":\"success\", \"data\": \"" +newAppo+ "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Appointments.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateAppointment(String AppointmentId,String PatientName, String DoctorName, String HosptlName, String phone,
			String email, String date, String time, String charge) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE appointment SET PatientName=?,DoctorName=?,HosptlName=?,phone=?,email=?,date=?,time=?,charge=? WHERE AppointmentId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
		
			preparedStmt.setString(1, PatientName);
			preparedStmt.setString(2, DoctorName);
			preparedStmt.setString(3, HosptlName);
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, date);
			preparedStmt.setString(7, time);
			preparedStmt.setFloat(8, Float.parseFloat(charge));
			preparedStmt.setInt(9, Integer.parseInt(AppointmentId));
	
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newAppo = viewAppointment();
			output = "{\"status\":\"success\", \"data\": \"" +newAppo+ "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the Appointments.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteAppointment(String AppointmentId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from appointment where AppointmentId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(AppointmentId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newAppo = viewAppointment();
			output = "{\"status\":\"success\", \"data\": \"" +newAppo+ "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Appointments.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}