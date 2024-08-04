package major.service;

import java.util.*;
import major.model.Major;

public interface MajorService {
	//查询全部
		public List <Major> listAll();
		
		//根据ID查询一个
		public Major listById( int id );
		
		//根据条件查询部分学生
		public List <Major> list(Major major);
		
		//添加一个
		public boolean save( Major major );
		
		//根据ID修改一个
		public boolean update( Major major );
		
		//根据ID删除一个
		public boolean del(int id);
}
