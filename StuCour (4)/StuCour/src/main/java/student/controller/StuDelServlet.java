package student.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.service.StuService;
import student.service.imp.StuServiceImp;

/**
 * Servlet implementation class StuDelServlet
 */
@WebServlet("/StuDelServlet")
public class StuDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		StuService stuService = new StuServiceImp();
		stuService.del(  Integer.parseInt(id) );
		response.sendRedirect("StuListServlet");
	}

}
