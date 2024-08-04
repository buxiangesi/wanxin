package stucour.controller;

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
import stucour.service.StuCourService;
import stucour.service.imp.StuCourServiceImp;
import student.model.Student;

/**
 * Servlet implementation class StuCour
 */
@WebServlet("/StuCourServlet")
public class StuCourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String stuId = request.getParameter("id");
		Student stu = new Student();
		stu.setId( Integer.parseInt(stuId) );
		StuCourService stuCourService = new StuCourServiceImp();
		List <Course> selCours = stuCourService.ListCourByStu(stu);
		CourService courService = new CourServiceImp();
		List <Course> cours = courService.listAll();
		for(Course cour : cours) {
			cour.setSelected("0");
			for(Course selCour : selCours) {
				if(cour.getId() == selCour.getId()) {
					cour.setSelected("1");
				}
			}
		}
		request.setAttribute("stu", stu);
		request.setAttribute("cours", cours);
		request.getRequestDispatcher("stucour/stu_cour.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String stuId = request.getParameter("stuId");
		String [] courIds = request.getParameterValues("selCours");
		StuCourService stuCourService = new StuCourServiceImp();
		Student stu = new Student();
		stu.setId( Integer.parseInt(stuId) );
		stuCourService.delByStu(stu);
		Course cour = new Course();
		for(String courId : courIds) {
			cour.setId( Integer.parseInt(courId) );
			stuCourService.save(stu, cour);
		}
		response.sendRedirect("StuListServlet");
	}

}
