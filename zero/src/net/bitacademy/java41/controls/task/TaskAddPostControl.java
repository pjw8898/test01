package net.bitacademy.java41.controls.task;

import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.TaskService;
import net.bitacademy.java41.vo.MemberProject;
import net.bitacademy.java41.vo.Task;

public class TaskAddPostControl  implements PageControl {
	TaskService taskService;

	public TaskAddPostControl setTaskService(TaskService taskService) {
		this.taskService = taskService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		HttpSession session = (HttpSession)model.get("session");
		Task task = (Task)session.getAttribute("task");
	
		@SuppressWarnings("unchecked")
		Map<String,String[]> params = 
				(Map<String,String[]>)model.get("params");
		
		
		int status;
		
		if(params.get("status")[0].equals("") ){
			status = 1;
			
		} else {
			status = Integer.parseInt(params.get("status")[0]);
		}
		
		if(params.get("title")[0] != null){
		taskService.addProjectTask(new Task()
						.setPno(Integer.parseInt(params.get("pno")[0]))
						.setTitle(params.get("title")[0])
						.setUiProtoUrl(params.get("uiprotourl")[0])
						.setContent(params.get("content")[0])
						.setStartDate(Date.valueOf(params.get("startdate")[0]))
						.setEndDate(Date.valueOf(params.get("enddate")[0]))
						.setStatus(status)
						 );
			return "/task/addsuccess.jsp?no="+params.get("pno")[0];
		} else {
			return "/task/addfail.jsp";
		}
	}
}
	










