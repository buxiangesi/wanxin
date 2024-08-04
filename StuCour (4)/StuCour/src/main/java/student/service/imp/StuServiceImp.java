package student.service.imp;

import java.util.List;

import student.dao.StuDao;
import student.dao.imp.StuDaoImp;
import student.model.Student;
import student.service.StuService;

public class StuServiceImp implements StuService{
	StuDao stuDao = new StuDaoImp();
	@Override
	public List<Student> listAll() {
		// TODO Auto-generated method stub
		return stuDao.listAll();
	}

	@Override
	public Student listById(int id) {
		// TODO Auto-generated method stub
		return stuDao.listById(id);
	}
	
	@Override
	public List<Student> list(Student stu) {
		// TODO Auto-generated method stub
		return stuDao.list(stu);
	}

	@Override
	public boolean save(Student stu) {
		// TODO Auto-generated method stub
		return stuDao.save(stu);
	}

	@Override
	public boolean update(Student stu) {
		// TODO Auto-generated method stub
		return stuDao.update(stu);
	}

	@Override
	public boolean del(int id) {
		// TODO Auto-generated method stub
		return stuDao.del(id);
	}

}
