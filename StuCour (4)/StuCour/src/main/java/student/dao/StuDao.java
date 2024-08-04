package student.dao;

import java.util.*;
import student.model.*;

public interface StuDao {
	//查询全部
	public List <Student> listAll();
	
	//根据ID查询一个
	public Student listById( int id );
	
	//根据条件查询部分学生
	public List <Student> list(Student stu);
	
	//添加一个
	public boolean save( Student stu );
	
	//根据ID修改一个
	public boolean update( Student stu );
	
	//根据ID删除一个
	public boolean del(int id);
}
