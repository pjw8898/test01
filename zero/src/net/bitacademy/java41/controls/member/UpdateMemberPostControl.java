package net.bitacademy.java41.controls.member;


import java.util.Map;

import net.bitacademy.java41.Annotations.Component;
import net.bitacademy.java41.controls.PageControl;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.vo.Member;

@Component("/member/postupdateMemberInfo.do")
public class UpdateMemberPostControl implements PageControl {
	
	MemberService memberService;
	
	public UpdateMemberPostControl setMemberService(MemberService memberService) {
		this.memberService = memberService;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
				
		@SuppressWarnings("unchecked")
		Map<String,String[]> params = 
				(Map<String,String[]>)model.get("params");
			
			String serverpwd = params.get("serverpwd")[0];
		
			if(params.get("password")[0].equals(serverpwd)){
				Member member = new Member()
				.setEmail(params.get("email")[0])
				.setName(params.get("name")[0])
				.setTel(params.get("tel")[0])
				.setBlog(params.get("blog")[0])
				.setDetailAddress(params.get("detailAddr")[0])
				.setTag(params.get("tag")[0])
				.setLevel(Integer.parseInt((params.get("level")[0])));
				
				memberService.adminChange(member);
				
				model.put("memberInfo", member);
				
				return "viewsucess.jsp";
		} else {
				
				return "redirect:../member/viewfail.jsp";
		
		}
		
	}
}













