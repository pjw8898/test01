package net.bitacademy.java41.controls.task;

import java.util.Map;

import net.bitacademy.java41.Annotations.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.TaskService;


@Component("/task/updatetask.do")
public class TaskUpdateControl implements PageControl {
	

	TaskService taskService;
	

	public TaskUpdateControl setTaskService(TaskService taskService) {
		this.taskService = taskService;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Map<String,String[]> params = 
									(Map<String,String[]>)model.get("params");
		
		int tno = Integer.parseInt(params.get("tno")[0]);
		
		model.put("taskInfo", taskService.getTask(tno));		
		
		return "/task/updateTask.jsp";

		}
	}


















