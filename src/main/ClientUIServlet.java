package main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.api.client.ClientResponse;

import jaxb.xml.Student;
import jaxb.xml.Workitem;
/**
 * Servlet implementation class ClientUIServlet
 */
@WebServlet("/ClientUIServlet")
public class ClientUIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public ClientUIServlet() {
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
		// TODO Auto-generated method stub
		CRUDClient client = new CRUDClient();
		
		if(request.getParameter("task").equalsIgnoreCase("View Grade")){
			String id = request.getParameter("studentID");
			String itemname = request.getParameter("assignID");				
			if (itemname == ""){
				itemname = "NoAssign";
			}
			ClientResponse clientResponse = client.viewGrade(id,itemname);
			int code = clientResponse.getStatus();
			String responseoutput;
	    	if(code == 200){
	    		if(!itemname.equalsIgnoreCase("NoAssign")){
	    			Workitem xml = clientResponse.getEntity(Workitem.class);
		    		if(xml.getGrades() == null || xml.getGrades().equalsIgnoreCase("")){
		    			responseoutput = "Http Code : "+ code + "<br>Grade for " + "Student " + id + " and WorkItem " + itemname + " has not been posted yet.";
		    		}else
		    		responseoutput = "Http Code : "+ code + "<br>Student ID : " + id + "<br>WorkItem : " + itemname + "<br>Grade : " + xml.getGrades() + "<br>Feedback : " + xml.getFeedback();
	    		}else{
	    			Student xml = clientResponse.getEntity(Student.class);
	    			ArrayList<Workitem> itemlist = new ArrayList<Workitem>();
	    			itemlist = xml.getWorkitemlist();
	    			responseoutput = "Http Code : "+ code + "<br>Student : " + id;
	    			for(Workitem item:itemlist){
	    				if(item.getGrades() == null || item.getGrades().equalsIgnoreCase("")){
			    			responseoutput += " <br>Grade for WorkItem " + item.getItemname() + " has not been posted yet.";
			    		}else
			    		responseoutput += "<br>WorkItem : " + item.getItemname() + "<br>Grade : " + item.getGrades() + "<br>Feedback : " + item.getFeedback();	
	    			}	
	    		} 
	    	}else if(code == 404){
	    		responseoutput = "Http Code : "+ code + "<br>Student : " + id + " not found"; 
	    	}
	    	else if(code == 409){
	    		responseoutput = "Http Code : "+ code + "<br>Workitem : " + itemname + " not found for student : " + id; 
	    	}else{
	    		responseoutput = "Http Code : "+ code + "<br>" + clientResponse.getStatusInfo();
	    	}
	    	request.setAttribute("message", responseoutput);
	    	request.getRequestDispatcher("/home.jsp").forward(request, response);
		}	
    }
}
