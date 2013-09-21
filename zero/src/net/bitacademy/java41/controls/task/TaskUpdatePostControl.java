package net.bitacademy.java41.controls.task;


import java.sql.Date;
import java.util.Map;

import net.bitacademy.java41.Annotations.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.TaskService;
import net.bitacademy.java41.vo.Task;

@Component("/task/taskupdatepost.do")
public class TaskUpdatePostControl implements PageControl {
	
	TaskService taskService;
	
	public TaskUpdatePostControl setProjectService(TaskService taskService) {
		this.taskService = taskService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
				
		@SuppressWarnings("unchecked")
		Map<String,String[]> params = 
								(Map<String,String[]>)model.get("params");
		
		int status;
		
		if(params.get("status")[0].equals("") ){
			status = 0;
			
		} else {
			status = Integer.parseInt(params.get("status")[0]);
		}
//		System.out.println(Integer.parseInt(parasms.get("tno")[0]));
		
		int tno = Integer.parseInt(params.get("tno")[0]);
		
		if(tno <= 0)  {
			return "/task/updatefail.jsp?tno="+tno;
			
		} else {
				taskService.change(new Task()
				.setTitle(params.get("title")[0])
				.setUiProtoUrl(params.get("uiProtoUrl")[0])
				.setContent(params.get("content")[0])
				.setStartDate(Date.valueOf(params.get("startDate")[0]))
				.setEndDate(Date.valueOf(params.get("endDate")[0]))
				.setStatus(status)
				.setTno(tno)
				.setPno(Integer.parseInt(params.get("pno")[0]))
						);
				
				return "/task/updatesuccess.jsp?tno="+tno;
			}
		}
	}













