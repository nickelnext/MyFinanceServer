package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainpackage.VersionHandler;

/**
 * Servlet implementation class VersionServlet
 */
@WebServlet("/VersionServlet")
public class VersionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VersionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String clientVersion = request.getParameter("clientVersion");
		String serverVersion = VersionHandler.getVersion();

		String result = "";

		//version is null or empty, or clientHASH is different from serverHASH
		if(clientVersion==null || clientVersion.isEmpty() || clientVersion!=serverVersion)
			result = VersionHandler.getVersionStuff();
		//returns an "OK" withouth all the data.
		else
			result = "OK";
		//pack or not?
		//TODO
		response.getWriter().write(result);
	}

}
