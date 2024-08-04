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
import student.service.imp.StuServiceImp;
import student.model.Student;
import student.service.*;
/**
 * Servlet implementation class StuListServlet
 */
@WebServlet("/StuListServlet")
public class StuListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		StuService stuService = new StuServiceImp();
		MajorService majorService = new MajorServiceImp();
		List <Student> stus = stuService.listAll();
		List <Major> majors = majorService.listAll();
		request.setAttribute("stus", stus);
		request.setAttribute("majors", majors);
		request.getRequestDispatcher("student/stu_list.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id").trim();
		String name = request.getParameter("name").trim();
		String sex = request.getParameter("sex").trim();
		String age = request.getParameter("age").trim();
		String majorId = request.getParameter("majorId").trim();
		Student stu = new Student();
		Major major = new Major();
		if(id != null && !id.equals(""))stu.setId( Integer.parseInt(id) );
		if(name != null && !name.equals(""))stu.setName( name );
		if(sex != null && !sex.equals(""))stu.setSex( sex );
		if(age != null && !age.equals(""))stu.setAge( Integer.parseInt(age) );
		if(majorId != null && !majorId.equals("")) {
			major.setId(Integer.parseInt(majorId));
			stu.setMajor(major);
		}
		StuService stuService = new StuServiceImp();
		MajorService majorService = new MajorServiceImp();
		List <Student> stus = stuService.list(stu);
		List <Major> majors = majorService.listAll();
		request.setAttribute("majors", majors);
		request.setAttribute("stu", stu);
		request.setAttribute("stus", stus);
		request.getRequestDispatcher("student/stu_list.jsp").forward(request, response);
	}

}
