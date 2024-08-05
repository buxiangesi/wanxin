package com.shggc.project.sjtb.search.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shggc.project.sjtb.search.domain.DtoFormWzChildren;


public interface SearchMapper {
	
	public List<DtoFormWzChildren> selectDtoFormWzChildrenList();
	
	public List <HashMap<String, Object>> selectFwtjlb(@Param("jds") List<String> jds, @Param("xz")String xz, @Param("lx")String lx, @Param("cb")String cb);
	
	public Double selectJzmj(@Param("jds") List<String> jds, @Param("xz")String xz, @Param("lx")String lx, @Param("cb")String cb);
	
	public List <HashMap<String, Object>> selectQllx(@Param("jds") List<String> jds, @Param("xz")String xz, @Param("lx")String lx, @Param("cb")String cb);
	
	/*查询房产数*/
	public Double selectFcs(@Param("jds") List<String> jds, @Param("xz")String xz, @Param("lx")String lx, @Param("cb")String cb);
	
	/*查询产权人数*/
	public Double selectCqrs(@Param("jds") List<String> jds, @Param("xz")String xz, @Param("lx")String lx, @Param("cb")String cb);
	
	/*查询居主人数*/
	public Double selectJzrs(@Param("jds") List<String> jds, @Param("xz")String xz, @Param("lx")String lx, @Param("cb")String cb);
	
	/*查询房间数*/
	public Double selectFjs(@Param("jds") List<String> jds, @Param("xz")String xz, @Param("lx")String lx, @Param("cb")String cb);
	
	/*查询土地使用面具*/
	public Double selectTdsymj(@Param("jds") List<String> jds, @Param("xz")String xz, @Param("lx")String lx, @Param("cb")String cb);
	
	public List <HashMap<String, Object>> selectQlxz(@Param("jds") List<String> jds, @Param("xz")String xz, @Param("lx")String lx, @Param("cb")String cb);
}
