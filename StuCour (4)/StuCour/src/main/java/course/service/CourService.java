package course.service;

import java.util.*;

import course.model.*;

public interface CourService {
	//查询全部
		public List <Course> listAll();
		
		//根据ID查询一个
		public Course listById( int id );
		
		//根据条件查询部分学生
		public List <Course> list(Course cour);
		
		//添加一个
		public boolean save( Course cour );
		
		//根据ID修改一个
		public boolean update( Course cour );
		
		//根据ID删除一个
		public boolean del(int id);
}
