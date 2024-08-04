package course.service.imp;

import java.util.List;

import course.dao.CourDao;
import course.dao.imp.CourDaoImp;
import course.model.Course;
import course.service.CourService;


public class CourServiceImp implements CourService{
	CourDao courDao = new CourDaoImp();

	@Override
	public List<Course> listAll() {
		// TODO Auto-generated method stub
		return courDao.listAll();
	}

	@Override
	public Course listById(int id) {
		// TODO Auto-generated method stub
		return courDao.listById(id);
	}

	@Override
	public List<Course> list(Course cour) {
		// TODO Auto-generated method stub
		return courDao.list(cour);
	}

	@Override
	public boolean save(Course cour) {
		// TODO Auto-generated method stub
		return courDao.save(cour);
	}

	@Override
	public boolean update(Course cour) {
		// TODO Auto-generated method stub
		return courDao.update(cour);
	}

	@Override
	public boolean del(int id) {
		// TODO Auto-generated method stub
		return courDao.del(id);
	}
	

}
