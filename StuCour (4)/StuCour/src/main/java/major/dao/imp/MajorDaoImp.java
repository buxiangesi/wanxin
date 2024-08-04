package major.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DataSource;
import major.dao.MajorDao;
import major.model.Major;

public class MajorDaoImp implements MajorDao{

	@Override
	public List<Major> listAll() {
		// TODO Auto-generated method stub
		// ********************从数据库读取数据(Control)*****************************
				Connection con = null;
				ArrayList <Major> majors = new ArrayList <Major> ();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from major");
					while( rs.next() ) {
						Major major = new Major();
						major.setId( rs.getInt("id") );
						major.setName( rs.getString("name") );
						majors.add(major);
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
				return majors;
	}

	@Override
	public Major listById(int id) {
		Connection con = null;
		Major major = new Major();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from major where id=" + id);
			rs.next();
			major.setId( rs.getInt("id") );
			major.setName( rs.getString("name") );
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			con.close();
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		return major;
	}

	@Override
	public List<Major> list(Major major) {
		// TODO Auto-generated method stub
		Connection con = null;
		ArrayList <Major> majors = new ArrayList <Major> ();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			Statement st = con.createStatement();
			String sql = "select * from major where 1=1";
			
			int id = major.getId();
			String name = major.getName();
			
			if( id != -1 ) {
				sql = sql + " and id=" + id;
			}
			if(name != null && !name.equals("")) {
				sql = sql + " and name like '%" + name + "%'";
			}
			ResultSet rs = st.executeQuery(sql);
			while( rs.next() ) {
				Major major1 = new Major();
				major1.setId( rs.getInt("id") );
				major1.setName( rs.getString("name") );
				majors.add(major1);
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
		return majors;
	}
	
	@Override
	public boolean save(Major major) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			String sql = "insert into major values (?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, major.getId());
			pst.setString(2, major.getName());
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
	public boolean update(Major major) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			String sql = "update major set name=? where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, major.getName());
			pst.setInt(2, major.getId());
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
			st.execute("delete from major where id = " + id);	
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
