package login.service.imp;

import login.dao.LoginDao;
import login.dao.imp.LoginDaoImp;
import login.model.User;
import login.service.LoginService;

public class LoginServiceImp implements LoginService{

	LoginDao loginDao = new LoginDaoImp();
	@Override
	public User listByUser(User user) {
		// TODO Auto-generated method stub
		return loginDao.listByUser(user);
	}

}
