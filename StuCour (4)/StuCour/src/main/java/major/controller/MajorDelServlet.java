package major.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import major.service.MajorService;
import major.service.imp.MajorServiceImp;


/**
 * Servlet implementation class CourDelServlet
 */
@WebServlet("/MajorDelServlet")
public class MajorDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		MajorService majorService = new MajorServiceImp();
		majorService.del(  Integer.parseInt(id) );
		response.sendRedirect("MajorListServlet");
	}

}
