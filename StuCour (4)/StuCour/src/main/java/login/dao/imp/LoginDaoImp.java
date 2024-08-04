package login.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.DataSource;
import login.dao.LoginDao;
import login.model.User;

public class LoginDaoImp implements LoginDao{

	@Override
	public User listByUser(User user) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DataSource.url , DataSource.username , DataSource.password);
			String sql = "select * from user where username=? and password=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			ResultSet rs = pst.executeQuery();
			if( !rs.next() ){
				return null;
			}
			return user;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
