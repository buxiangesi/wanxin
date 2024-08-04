package student.dao;

import java.util.*;
import student.model.*;

public interface StuDao {
	//��ѯȫ��
	public List <Student> listAll();
	
	//����ID��ѯһ��
	public Student listById( int id );
	
	//����������ѯ����ѧ��
	public List <Student> list(Student stu);
	
	//���һ��
	public boolean save( Student stu );
	
	//����ID�޸�һ��
	public boolean update( Student stu );
	
	//����IDɾ��һ��
	public boolean del(int id);
}
