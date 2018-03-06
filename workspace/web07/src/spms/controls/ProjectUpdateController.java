package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

@Component(value="/project/update.do")
public class ProjectUpdateController implements Controller, DataBinding {
	ProjectDao projectDao;
	
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project) model.get("project");
		if(project.getTitle() == null) {			//GET Request
			model.put("project", projectDao.selectOne((Integer)model.get("no")));
			return "/Project/ProjectUpdateForm.jsp";
		}else {
			projectDao.update(project);
			return "redirect:list.do";
		}
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class,
				"project", spms.vo.Project.class
		};
	}

}
