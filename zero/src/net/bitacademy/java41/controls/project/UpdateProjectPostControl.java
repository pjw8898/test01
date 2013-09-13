package net.bitacademy.java41.controls.project;


import java.sql.Date;
import java.util.Map;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;

public class UpdateProjectPostControl implements PageControl {
	
	ProjectService projectService;
	
	public UpdateProjectPostControl setProjectService(ProjectService projectService) {
		this.projectService = projectService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
				
		@SuppressWarnings("unchecked")
		Map<String,String[]> params = 
				(Map<String,String[]>)model.get("params");
		
		if (params.get("no")[0] != null) {
			Project project = new Project()
			.setTitle(params.get("title")[0])
			.setContent(params.get("content")[0])
			.setStartDate(Date.valueOf(params.get("startDate")[0]))
			.setEndDate(Date.valueOf(params.get("endDate")[0]))
			.setNo(Integer.parseInt(params.get("no")[0]))
			.setTag(params.get("tag")[0]);
			
			projectService.change(project);
			model.put("projectInfo", project);
				
			return "viewsucess.jsp";
		
		} else {
				return "redirect:../member/viewfail.jsp";
		}
	}
}













