package net.bitacademy.java41.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import net.bitacademy.java41.Annotations.Component;

@Component("/auth/logout.do")
public class LogoutControl implements PageControl {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		session.invalidate();
		return "redirect:loginForm.do";
	}
}












