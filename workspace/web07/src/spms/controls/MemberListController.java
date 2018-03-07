package spms.controls;

import java.util.Hashtable;
import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;

@Component(value="/member/list.do")
public class MemberListController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Map<String, Object> paramMap = new Hashtable<String, Object>();
		paramMap.put("orderCond", model.get("orderCond"));
		model.put("members", memberDao.selectList(paramMap));
		return "/Member/MemberList.jsp";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"orderCond", String.class
		};
	}
}
