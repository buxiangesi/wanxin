package course.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.model.Course;
import course.service.CourService;
import course.service.imp.CourServiceImp;

/**
 * Servlet implementation class StuUpdServlet
 */
@WebServlet("/CourUpdServlet")
public class CourUpdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		CourService courService = new CourServiceImp();
		Course cour = courService.listById( Integer.parseInt(id) );
		request.setAttribute("cour", cour);
		request.getRequestDispatcher("course/cour_upd.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String teacher = request.getParameter("teacher");
		Course cour = new Course();
		cour.setId( Integer.parseInt(id) );
		cour.setName(name);
		cour.setTeacher(teacher );
		CourService courService = new CourServiceImp();
		courService.update(cour);
		response.sendRedirect("CourListServlet");
	}

}
