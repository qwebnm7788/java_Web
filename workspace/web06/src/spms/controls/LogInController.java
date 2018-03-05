package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

@Component(value="/auth/login.do")
public class LogInController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public LogInController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member) model.get("loginInfo");
		if (member.getEmail() == null) {
			return "/Auth/LogInForm.jsp";
		} else {
			Member detailedMember = memberDao.exist(member.getEmail(), member.getPassword());
			if (detailedMember != null) {
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("member", member);
				return "redirect:../member/list.do";
			} else {
				return "/Auth/LogInFail.jsp";
			}
		}
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"loginInfo", spms.vo.Member.class
		};
	}
}