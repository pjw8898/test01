package net.bitacademy.java41.controls.task;

import java.util.Map;

import net.bitacademy.java41.Annotations.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.TaskService;

@Component("/task/delete.do")
public class TaskDeleteControl implements PageControl{
TaskService taskService;
	
	public TaskDeleteControl setTaskService(TaskService taskService) {
		this.taskService = taskService;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		
		Map<String,String[]> params = 
							(Map<String,String[]>)model.get("params");
		
		int tno = Integer.parseInt(params.get("tno")[0]);
		
		model.put("remove", taskService.getTaskDelete(tno));

		return "/task/delete.jsp";

	}
}
