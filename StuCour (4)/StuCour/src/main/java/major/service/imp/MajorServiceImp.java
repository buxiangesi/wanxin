package major.service.imp;

import java.util.List;

import course.dao.CourDao;
import course.dao.imp.CourDaoImp;
import course.model.Course;
import course.service.CourService;
import major.dao.MajorDao;
import major.dao.imp.MajorDaoImp;
import major.model.Major;
import major.service.MajorService;


public class MajorServiceImp implements MajorService{
	MajorDao majorDao = new MajorDaoImp();

	@Override
	public List<Major> listAll() {
		// TODO Auto-generated method stub
		return majorDao.listAll();
	}

	@Override
	public Major listById(int id) {
		// TODO Auto-generated method stub
		return majorDao.listById(id);
	}

	@Override
	public List<Major> list(Major major) {
		// TODO Auto-generated method stub
		return majorDao.list(major);
	}

	@Override
	public boolean save(Major major) {
		// TODO Auto-generated method stub
		return majorDao.save(major);
	}

	@Override
	public boolean update(Major major) {
		// TODO Auto-generated method stub
		return majorDao.update(major);
	}

	@Override
	public boolean del(int id) {
		// TODO Auto-generated method stub
		return majorDao.del(id);
	}

	
}
