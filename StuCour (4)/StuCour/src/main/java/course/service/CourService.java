package course.service;

import java.util.*;

import course.model.*;

public interface CourService {
	//��ѯȫ��
		public List <Course> listAll();
		
		//����ID��ѯһ��
		public Course listById( int id );
		
		//����������ѯ����ѧ��
		public List <Course> list(Course cour);
		
		//���һ��
		public boolean save( Course cour );
		
		//����ID�޸�һ��
		public boolean update( Course cour );
		
		//����IDɾ��һ��
		public boolean del(int id);
}
