package com.xunx.pgywxy.web.action.admin;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.xunx.pgywxy.entity.member.MemberRank;
import com.xunx.pgywxy.service.member.MemberRankService;

/**
 * ClassName: MemberRankAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(��ѡ). <br/>
 * date: 2012-8-21 ����9:49:12 <br/>
 *
 * @version 
 * @since JDK 1.6
 */
@ParentPackage("admin")
public class MemberRankAction extends BaseAdminAction {
	private static final long serialVersionUID = 1L;

	@Resource(name = "memberRankService")
	private MemberRankService memberRankService;

	private MemberRank memberRank;

	// 是否已存在 ajax验证
	public String checkName() {
		String oldValue = getParameter("oldValue");
		String newValue = memberRank.getName();
		if (memberRankService.isUniqueByName(oldValue, newValue)) {
			return ajax("true");
		}
		else {
			return ajax("false");
		}
	}

	// �б�
	public String list() {
		pager = memberRankService.findPager(pager);
		return LIST;
	}

	// ɾ��
	public String delete() throws Exception {
		memberRankService.delete(ids);
		return ajax(Status.success, "ɾ��ɹ�!");
	}

	// ���
	public String add() {
		return INPUT;
	}

	// �༭
	public String edit() {
		memberRank = memberRankService.load(id);
		return INPUT;
	}

	// ����
	@Validations(requiredStrings = {

	}, requiredFields = {

	})
	@InputConfig(resultName = "error")
	public String save() throws Exception {
		memberRankService.save(memberRank);
		redirectUrl = "member_rank!list.action";
		return SUCCESS;
	}

	// ����
	@Validations(requiredStrings = {

	}, requiredFields = {

	})
	@InputConfig(resultName = "error")
	public String update() throws Exception {
		MemberRank persistent = memberRankService.load(id);
		BeanUtils.copyProperties(memberRank, persistent, new String[] { "id", "createDate",
				"modifyDate" });
		memberRankService.update(persistent);
		redirectUrl = "member_rank!list.action";
		return SUCCESS;
	}

	public MemberRank getMemberRank() {
		return memberRank;
	}

	public void setMemberRank(MemberRank memberRank) {
		this.memberRank = memberRank;
	}
}
