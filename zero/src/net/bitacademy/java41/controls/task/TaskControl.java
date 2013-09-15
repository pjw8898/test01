package net.bitacademy.java41.controls.task;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.TaskService;
import net.bitacademy.java41.vo.Task;


public class TaskControl implements PageControl {
	TaskService taskService;

	public TaskControl setProjectService(TaskService taskService) {
		this.taskService = taskService;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Map<String, String[]> params = (Map<String, String[]>) model.get("params");
		HttpSession session = (HttpSession) model.get("session");
		ArrayList<Task> task = 
		 (ArrayList<Task>)taskService.list(Integer.parseInt(params.get("pno")[0]));

//			model.put("taskInfo", task);
			session.setAttribute("list", task);
			return "/task/task.jsp";
		


	} 
}