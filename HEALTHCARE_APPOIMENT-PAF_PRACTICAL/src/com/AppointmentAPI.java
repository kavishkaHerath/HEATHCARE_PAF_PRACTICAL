package com;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemAPI
 */
@WebServlet("/AppointmentAPI")
public class AppointmentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	Appointment AppObj = new Appointment();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = AppObj.insertAppointment(
				request.getParameter("PatientName"),
				request.getParameter("DoctorName"),
				request.getParameter("HosptlName"),
				request.getParameter("phone"),
				request.getParameter("email"),
				request.getParameter("date"),
				request.getParameter("time"),
				request.getParameter("charge"));
				response.getWriter().write(output); 
	}
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
	
	String[] p = param.split("=");
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = AppObj.updateAppointment(
			 paras.get("hidAppointmentIdSave").toString(),
			 paras.get("PatientName").toString(),
			 paras.get("DoctorName").toString(),
			 paras.get("HosptlName").toString(),
			 paras.get("phone").toString(),
			 paras.get("email").toString(),
			 paras.get("date").toString(),
			 paras.get("time").toString(),
			 paras.get("charge").toString());
			response.getWriter().write(output);
			}
protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = AppObj.deleteAppointment(paras.get("AppointmentId").toString());
			response.getWriter().write(output);
			}

}
