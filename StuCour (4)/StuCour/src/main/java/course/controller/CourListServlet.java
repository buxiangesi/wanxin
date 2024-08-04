package course.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import course.model.Course;
import course.service.CourService;
import course.service.imp.CourServiceImp;
/**
 * Servlet implementation class CourListServlet
 */
@WebServlet("/CourListServlet")
public class CourListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		CourService courService = new CourServiceImp();
		List <Course> cours = courService.listAll();
		request.setAttribute("cours", cours);
		request.getRequestDispatcher("course/cour_list.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id").trim();
		String name = request.getParameter("name").trim();
		String teacher = request.getParameter("teacher").trim();
		Course cour = new Course();
		if(id != null && !id.equals(""))cour.setId( Integer.parseInt(id) );
		if(name != null && !name.equals(""))cour.setName( name );
		if(teacher != null && !teacher.equals(""))cour.setTeacher( teacher );
		CourService courService = new CourServiceImp();
		List <Course> cours = courService.list(cour);
		request.setAttribute("cour", cour);
		request.setAttribute("cours", cours);
		request.getRequestDispatcher("course/cour_list.jsp").forward(request, response);
	}

}
