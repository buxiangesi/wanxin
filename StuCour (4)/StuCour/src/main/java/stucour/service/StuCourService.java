package stucour.service;

import java.util.List;

import course.model.Course;
import student.model.Student;

public interface StuCourService {
	//根据学生信息查询该学生所选的课程
		public List <Course> ListCourByStu(Student stu);
		
		//根据课程信息和学生筛选条件查询选课的学生
		public List <Student> ListStuByCour( Course cour , Student stu );
		
		//录入一条选课信息
		public boolean save( Student stu , Course cour);
		
		//根据学生删除该学生的所有选课信息
		public boolean delByStu(Student stu);
		
		//根据课程删除该课程的所有选课信息
		public boolean delByCour(Course cour);
		
		//根据学生和课程删除选课信息
		public boolean del(Student stu , Course cour);
}
