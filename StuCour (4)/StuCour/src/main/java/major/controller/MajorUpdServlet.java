package major.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import major.model.Major;
import major.service.MajorService;
import major.service.imp.MajorServiceImp;

/**
 * Servlet implementation class StuUpdServlet
 */
@WebServlet("/MajorUpdServlet")
public class MajorUpdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		MajorService majorService = new MajorServiceImp();
		Major major = majorService.listById( Integer.parseInt(id) );
		request.setAttribute("major", major);
		request.getRequestDispatcher("major/major_upd.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Major major = new Major();
		major.setId( Integer.parseInt(id) );
		major.setName(name);
		MajorService majorService = new MajorServiceImp();
		majorService.update(major);
		response.sendRedirect("MajorListServlet");
	}

}
