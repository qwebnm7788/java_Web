package spms.dao;

import java.util.List;
import java.util.Map;

import spms.vo.Project;

public interface ProjectDao {
	List<Project> selectList(Map<String, Object> paramMap) throws Exception;
	int insert(Project project) throws Exception;
	Project selectOne(int no) throws Exception;
	int update(Project project) throws Exception;
	int delete(int no) throws Exception;
}
