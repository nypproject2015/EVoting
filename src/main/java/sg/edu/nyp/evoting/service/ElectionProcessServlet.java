package sg.edu.nyp.evoting.service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletOutputStream;

import sg.edu.nyp.evoting.beans.*;

 
public class ElectionProcessServlet extends HttpServlet{
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException{
		try {
				String password1 = request.getParameter("password1");
				String password2 = request.getParameter("password2");
				String password3 = request.getParameter("password3");	
				
				(new EVotingService(password1, password2, password3)).startElectionProcess();
				
				response.getOutputStream().println("Election Process Started");
				
			} catch (Exception e) {
				e.printStackTrace();
				try {
					response.getOutputStream().println("Election Starting Process failed due to an Exception:"+e.getStackTrace());
				} catch(Exception ex) {}
			} 	
	}
}
