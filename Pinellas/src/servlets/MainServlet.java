package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonSyntaxException;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		//		doPost(request, response);
	}  	

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, 
	     HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		long startTime = System.currentTimeMillis();
		//		String username = request.getParameter("username");
		//		String password = request.getParameter("password");
		
		String jason = request.getParameter("json");
		String result = null;
		if(jason == null || jason.isEmpty())
		{
			//TODO
			//ritornare solo una stringa d'errore "ERROR_INVALID_JSON"
		}
		else
		{
			try {
				result = RequestHandler.doStuff(jason);
			} 
			catch (InstantiationException e)
			{
				// TODO
			} catch (IllegalAccessException e) {
				// TODO
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO
				e.printStackTrace();
			} catch (JsonSyntaxException e) {
				// TODO
				e.printStackTrace();
			}
			//		GZIPOutputStream gzipStream = new GZIPOutputStream(response.getOutputStream());
			//		gzipStream.write(result.getBytes());
			//		gzipStream.close();
			//		response.setHeader("Content-Encoding", "gzip"); 
			//		response.setContentType("text/html");
		}
		long endTime = System.currentTimeMillis();
		long seconds = (endTime - startTime) / 1000;

		System.out.println("Servlet totale: " + seconds + " secondi");
		response.getWriter().write(result);

	}   	  	    

}