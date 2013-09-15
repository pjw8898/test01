package net.bitacademy.java41.controls.task;

import java.util.Map;

import net.bitacademy.java41.controls.PageControl;

public class TaskAddControl implements PageControl {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String,String[]> params = 
				(Map<String,String[]>)model.get("params");
		
		model.put("pno", params.get("pno"));
		
		return "/task/addform.jsp";
		
	}
}


