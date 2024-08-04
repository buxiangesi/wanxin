package major.controller;

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
/**
 * Servlet implementation class CourListServlet
 */
@WebServlet("/MajorListServlet")
public class MajorListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		MajorService majorService = new MajorServiceImp();
		List <Major> majors = majorService.listAll();
		request.setAttribute("majors", majors);
		request.getRequestDispatcher("major/major_list.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id").trim();
		String name = request.getParameter("name").trim();
		Major major = new Major();
		if(id != null && !id.equals(""))major.setId( Integer.parseInt(id) );
		if(name != null && !name.equals(""))major.setName( name );
		MajorService majorService = new MajorServiceImp();
		List <Major> majors = majorService.list(major);
		request.setAttribute("major", major);
		request.setAttribute("majors", majors);
		request.getRequestDispatcher("major/major_list.jsp").forward(request, response);
	}

}
