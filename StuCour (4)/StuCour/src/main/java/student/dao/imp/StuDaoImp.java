package student.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DataSource;
import major.dao.MajorDao;
import major.dao.imp.MajorDaoImp;
import major.model.Major;
import student.dao.StuDao;
import student.model.Student;

public class StuDaoImp implements StuDao{
	MajorDao majorDao = new MajorDaoImp();
	@Override
	public List<Student> listAll() {
		// TODO Auto-generated method stub
		// ********************从数据库读取数据(Control)*****************************
				Connection con = null;
				ArrayList <Student> stus = new ArrayList <Student> ();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from student");
					while( rs.next() ) {
						Student stu = new Student();
						stu.setId( rs.getInt("id") );
						stu.setName( rs.getString("name") );
						stu.setSex( rs.getString("sex") );
						stu.setAge( rs.getInt("age") );
						Major major = majorDao.listById( rs.getInt("major_id") );
						stu.setMajor(major);
						stus.add(stu);
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
	public Student listById(int id) {
		Connection con = null;
		Student stu = new Student();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student where id=" + id);
			rs.next();
			stu.setId( rs.getInt("id") );
			stu.setName( rs.getString("name") );
			stu.setSex( rs.getString("sex") );
			stu.setAge( rs.getInt("age") );	
			Major major = majorDao.listById( rs.getInt("major_id") );
			stu.setMajor(major);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		return stu;
	}

	@Override
	public List<Student> list(Student stu) {
		// TODO Auto-generated method stub
		Connection con = null;
		ArrayList <Student> stus = new ArrayList <Student> ();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			Statement st = con.createStatement();
			String sql = "select * from student where 1=1";
			
			int id = stu.getId();
			String name = stu.getName();
			String sex = stu.getSex();
			int age = stu.getAge();
			Major major = stu.getMajor();
			
			if( id != -1 ) {
				sql = sql + " and id=" + id;
			}
			if(name != null && !name.equals("")) {
				sql = sql + " and name like '%" + name + "%'";
			}
			if(sex != null && !sex.equals("")) {
				sql = sql + " and sex ='" + sex + "'";
			}
			if( age != -1 ) {
				sql = sql + " and age=" + age;
			}
			if( null != major ) {
				sql = sql + " and major_id=" + major.getId();
			}
			ResultSet rs = st.executeQuery(sql);
			while( rs.next() ) {
				Student stu1 = new Student();
				stu1.setId( rs.getInt("id") );
				stu1.setName( rs.getString("name") );
				stu1.setSex( rs.getString("sex") );
				stu1.setAge( rs.getInt("age") );
				Major major1 = majorDao.listById( rs.getInt("major_id") );
				stu1.setMajor(major1);
				stus.add(stu1);
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
	public boolean save(Student stu) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			String sql = "insert into student values (?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, stu.getId());
			pst.setString(2, stu.getName());
			pst.setString(3, stu.getSex());
			pst.setInt(4, stu.getAge());
			pst.setInt(5, stu.getMajor().getId());
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
	public boolean update(Student stu) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			String sql = "update student set name=?,sex=?,age=?,major_id=? where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, stu.getName());
			pst.setString(2, stu.getSex());
			pst.setInt(3, stu.getAge());
			pst.setInt(4, stu.getMajor().getId());
			pst.setInt(5, stu.getId());
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
			st.execute("delete from student where id = " + id);	
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
