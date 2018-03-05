package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

@Component(value="/project/add.do")
public class ProjectAddController implements Controller, DataBinding {
	ProjectDao projectDao;
	
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project) model.get("project");
		if(project.getTitle() == null) {			//GET Request
			return "/Project/ProjectForm.jsp";
		}else {										//POST Request
			projectDao.insert(project);
			return "redirect:list.do";
		}
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"project", spms.vo.Project.class	
		};
	}
}
