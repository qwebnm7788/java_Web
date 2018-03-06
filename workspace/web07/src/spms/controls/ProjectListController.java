package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.dao.ProjectDao;

@Component(value="/project/list.do")
public class ProjectListController implements Controller {
	ProjectDao projectDao;
	
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("projects", projectDao.selectList());
		return "/Project/ProjectList.jsp";
	}

}
