package net.bitacademy.java41.controls.task;

import java.util.Map;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.TaskService;

public class TaskViewControl implements PageControl {
	TaskService taskService;

	public TaskViewControl setTaskService(TaskService taskService) {
		this.taskService = taskService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String,String[]> params = 
				(Map<String,String[]>)model.get("params");
		int tno = Integer.parseInt(params.get("tno")[0]);
		model.put("task", taskService.getTask(tno));

		return "/task/taskview.jsp";
	}
}













