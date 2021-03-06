package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

@Component(value="/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member) model.get("member");
		if(member.getEmail() == null) {
			//GET
			model.put("member", memberDao.selectOne((Integer)model.get("no")));
			return "/Member/MemberUpdateForm.jsp";
		}else {
			//POST
			memberDao.update(member);
			return "redirect:list.do";
		}
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"no", Integer.class,
			"member", spms.vo.Member.class
		};
	}

}
