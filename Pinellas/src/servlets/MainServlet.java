package servlets;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainpackage.RequestHandler;


/**
 * Servlet implementation class for Servlet: HelloServlet
 *
 */
 public class MainServlet extends javax.servlet.http.HttpServlet 
     implements javax.servlet.Servlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, 
	     HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, 
	     HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		String jason = request.getParameter("json");
		String result = null;
		
		try {
			result = RequestHandler.doStuff(jason);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GZIPOutputStream gzipStream = new GZIPOutputStream(response.getOutputStream());
//		gzipStream.write(result.getBytes());
//		gzipStream.close();
		response.setHeader("Content-Encoding", "gzip"); 
		response.setContentType("text/html"); 

		response.getWriter().write(result);
		
		
		// TODO Auto-generated method stub
	}   	  	    
	
}