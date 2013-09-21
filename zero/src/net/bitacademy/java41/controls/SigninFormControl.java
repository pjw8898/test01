package net.bitacademy.java41.controls;

import java.util.Map;

import net.bitacademy.java41.Annotations.Component;

@Component("/member/signinForm.do")
public class SigninFormControl implements PageControl {
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		return "/member/signinForm.jsp";
	}
}











