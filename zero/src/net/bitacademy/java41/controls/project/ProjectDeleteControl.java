package net.bitacademy.java41.controls.project;

import java.util.Map;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.ProjectService;

public class ProjectDeleteControl implements PageControl{
	ProjectService projectService;

	
	public ProjectDeleteControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}


	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String,String[]> params = 
							(Map<String,String[]>)model.get("params");
		
		int no = Integer.parseInt(params.get("no")[0]);
		
		model.put("remove", projectService.getProjectDelete(no));

		return "/project/delete.jsp";

	}
}

