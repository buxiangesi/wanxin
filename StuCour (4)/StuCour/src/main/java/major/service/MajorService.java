package major.service;

import java.util.*;
import major.model.Major;

public interface MajorService {
	//��ѯȫ��
		public List <Major> listAll();
		
		//����ID��ѯһ��
		public Major listById( int id );
		
		//����������ѯ����ѧ��
		public List <Major> list(Major major);
		
		//���һ��
		public boolean save( Major major );
		
		//����ID�޸�һ��
		public boolean update( Major major );
		
		//����IDɾ��һ��
		public boolean del(int id);
}
