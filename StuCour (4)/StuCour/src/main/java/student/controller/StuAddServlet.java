package student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import major.model.Major;
import major.service.MajorService;
import major.service.imp.MajorServiceImp;
import student.model.Student;
import student.service.StuService;
import student.service.imp.StuServiceImp;

/**
 * Servlet implementation class StuAddServlet
 */
@WebServlet("/StuAddServlet")
public class StuAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MajorService majorService = new MajorServiceImp();
		List <Major> majors = majorService.listAll();		
		request.setAttribute("majors", majors);
		request.getRequestDispatcher("student/stu_add.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String majorId = request.getParameter("majorId");
		Student stu = new Student();
		stu.setId( Integer.parseInt(id) );
		stu.setName(name);
		stu.setSex(sex);
		stu.setAge( Integer.parseInt(age) );
		Major major = new Major();
		major.setId( Integer.parseInt(majorId) );
		stu.setMajor(major);
		StuService stuService = new StuServiceImp();
		stuService.save(stu);
		response.sendRedirect("StuListServlet");
	}
}
