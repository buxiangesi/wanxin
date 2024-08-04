package course.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DataSource;
import course.dao.CourDao;
import course.model.Course;

public class CourDaoImp implements CourDao{

	@Override
	public List<Course> listAll() {
		// TODO Auto-generated method stub
		// ********************从数据库读取数据(Control)*****************************
				Connection con = null;
				ArrayList <Course> cours = new ArrayList <Course> ();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from course");
					while( rs.next() ) {
						Course cour = new Course();
						cour.setId( rs.getInt("id") );
						cour.setName( rs.getString("name") );
						cour.setTeacher( rs.getString("teacher") );
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
	public Course listById(int id) {
		Connection con = null;
		Course cour = new Course();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from course where id=" + id);
			rs.next();
			cour.setId( rs.getInt("id") );
			cour.setName( rs.getString("name") );
			cour.setTeacher( rs.getString("teacher") );	
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		return cour;
	}

	@Override
	public List<Course> list(Course cour) {
		// TODO Auto-generated method stub
		Connection con = null;
		ArrayList <Course> cours = new ArrayList <Course> ();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			Statement st = con.createStatement();
			String sql = "select * from course where 1=1";
			
			int id = cour.getId();
			String name = cour.getName();
			String teacher = cour.getTeacher();
			
			if( id != -1 ) {
				sql = sql + " and id=" + id;
			}
			if(name != null && !name.equals("")) {
				sql = sql + " and name like '%" + name + "%'";
			}
			if(teacher != null && !teacher.equals("")) {
				sql = sql + " and teacher like '%" + teacher + "%'";
			}
			ResultSet rs = st.executeQuery(sql);
			while( rs.next() ) {
				Course cour1 = new Course();
				cour1.setId( rs.getInt("id") );
				cour1.setName( rs.getString("name") );
				cour1.setTeacher( rs.getString("teacher") );
				cours.add(cour1);
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
	public boolean save(Course cour) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			String sql = "insert into course values (?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, cour.getId());
			pst.setString(2, cour.getName());
			pst.setString(3, cour.getTeacher());
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
	public boolean update(Course cour) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			String sql = "update course set name=?,teacher=? where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cour.getName());
			pst.setString(2, cour.getTeacher());
			pst.setInt(3, cour.getId());
			return pst.execute();
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
	public boolean del(int id) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			Statement st = con.createStatement();
			st.execute("delete from course where id = " + id);	
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
