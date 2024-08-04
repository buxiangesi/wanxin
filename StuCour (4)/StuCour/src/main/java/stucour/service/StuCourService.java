package stucour.service;

import java.util.List;

import course.model.Course;
import student.model.Student;

public interface StuCourService {
	//����ѧ����Ϣ��ѯ��ѧ����ѡ�Ŀγ�
		public List <Course> ListCourByStu(Student stu);
		
		//���ݿγ���Ϣ��ѧ��ɸѡ������ѯѡ�ε�ѧ��
		public List <Student> ListStuByCour( Course cour , Student stu );
		
		//¼��һ��ѡ����Ϣ
		public boolean save( Student stu , Course cour);
		
		//����ѧ��ɾ����ѧ��������ѡ����Ϣ
		public boolean delByStu(Student stu);
		
		//���ݿγ�ɾ���ÿγ̵�����ѡ����Ϣ
		public boolean delByCour(Course cour);
		
		//����ѧ���Ϳγ�ɾ��ѡ����Ϣ
		public boolean del(Student stu , Course cour);
}
