package login.dao;

import login.model.User;

public interface LoginDao {
	public User listByUser(User user);
}
