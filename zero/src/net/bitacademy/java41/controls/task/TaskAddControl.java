//package net.bitacademy.java41.controls.task;
//
//import java.sql.Date;
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//
//import net.bitacademy.java41.controls.PageControl;
//import net.bitacademy.java41.services.TaskService;
//import net.bitacademy.java41.vo.Member;
//import net.bitacademy.java41.vo.ProjectTask;
//
//public class TaskAddControl  implements PageControl {
//	TaskService taskService;
//
//	public TaskAddControl setTaskService(TaskService taskService) {
//		this.taskService = taskService;
//		return this;
//	}
//	
//	@Override
//	public String execute(Map<String, Object> model) throws Exception {
//		HttpSession session = (HttpSession)model.get("session");
//		Member member = (Member)session.getAttribute("member");
//		
//		@SuppressWarnings("unchecked")
//		Map<String,String[]> params = 
//				(Map<String,String[]>)model.get("params");
//		
//		taskService.addProjectTask(new ProjectTask()
//						.setTitle(params.get("title")[0])
//						.setUiProtoUrl(params.get("uiProtoUrl")[0])
//						.setContent(params.get("content")[0])
//						.setStartDate(
//							Date.valueOf(params.get("startDate")[0]))
//						.setEndDate(
//							Date.valueOf(params.get("endDate")[0]))
//						.setStatus(Integer.parseInt(params.get("status")[0]))
//						 );
//		
//		return "redirect:list.do";
//	}
//}
//
//
//
//
//
//
//
//
//
//
