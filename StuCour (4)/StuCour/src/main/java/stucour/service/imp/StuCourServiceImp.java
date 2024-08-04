package stucour.service.imp;

import java.util.List;

import course.model.Course;
import stucour.dao.StuCourDao;
import stucour.dao.imp.StuCourDaoImp;
import stucour.service.StuCourService;
import student.model.Student;

public class StuCourServiceImp implements StuCourService{

	StuCourDao stuCourDao = new StuCourDaoImp();
	@Override
	public List<Course> ListCourByStu(Student stu) {
		// TODO Auto-generated method stub
		return stuCourDao.ListCourByStu(stu);
	}

	@Override
	public List<Student> ListStuByCour(Course cour, Student stu) {
		// TODO Auto-generated method stub
		return stuCourDao.ListStuByCour(cour, stu);
	}

	@Override
	public boolean save(Student stu, Course cour) {
		// TODO Auto-generated method stub
		return stuCourDao.save(stu, cour);
	}

	@Override
	public boolean delByStu(Student stu) {
		// TODO Auto-generated method stub
		return stuCourDao.delByStu(stu);
	}

	@Override
	public boolean delByCour(Course cour) {
		// TODO Auto-generated method stub
		return stuCourDao.delByCour(cour);
	}

	@Override
	public boolean del(Student stu, Course cour) {
		// TODO Auto-generated method stub
		return stuCourDao.del(stu, cour);
	}

}
