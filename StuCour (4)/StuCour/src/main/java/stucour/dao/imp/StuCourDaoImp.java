package stucour.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DataSource;
import course.dao.CourDao;
import course.dao.imp.CourDaoImp;
import course.model.Course;
import major.model.Major;
import stucour.dao.StuCourDao;
import student.model.Student;
import student.service.StuService;
import student.service.imp.StuServiceImp;

public class StuCourDaoImp implements StuCourDao {

	@Override
	public List<Course> ListCourByStu(Student stu) {		
		Connection con = null;
		ArrayList <Course> cours = new ArrayList <Course> ();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from stucour where student_id="+ stu.getId());
			CourDao courDao = new CourDaoImp();
			while( rs.next() ) {
				int courseId = rs.getInt("course_id");
				Course cour = courDao.listById(courseId);
				cours.add(cour);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		return cours;
	}

	@Override
	public List<Student> ListStuByCour(Course cour, Student stu) {
		// TODO Auto-generated method stub
		Connection con = null;
		ArrayList <Student> stus = new ArrayList <Student> ();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from stucour where course_id=" + cour.getId());
			StuService stuService = new StuServiceImp();
			while( rs.next() ) {
				int stuId = rs.getInt("student_id");
				stu.setId(stuId);
				List <Student> stu1s = stuService.list(stu);
				for(Student stu1 : stu1s) {
					stus.add(stu1);
				}
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
		con.close();
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		return stus;
	}

	@Override
	public boolean save(Student stu, Course cour) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			String sql = "insert into stucour (student_id,course_id) values (?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, stu.getId());
			pst.setInt(2, cour.getId());
			pst.execute();	
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
	
	}

	@Override
	public boolean delByStu(Student stu) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			Statement st = con.createStatement();
			st.execute("delete from stucour where student_id = " + stu.getId());	
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public boolean delByCour(Course cour) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			Statement st = con.createStatement();
			st.execute("delete from stucour where course_id = " + cour.getId());	
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public boolean del(Student stu, Course cour) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			String sql = "delete from stucour where student_id=? and course_id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, stu.getId());
			pst.setInt(2, cour.getId());
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
