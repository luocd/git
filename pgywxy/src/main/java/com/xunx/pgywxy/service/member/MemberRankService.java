package com.xunx.pgywxy.service.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunx.pgywxy.bean.Pager;
import com.xunx.pgywxy.dao.member.MemberRankDao;
import com.xunx.pgywxy.entity.member.MemberRank;

/**
 * ClassName:MemberRankService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2012-8-18 ����11:56:15 <br/>
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service("memberRankService")
@Transactional
public class MemberRankService {
	@Resource(name = "memberRankDao")
	private MemberRankDao memberRankDao;

	@Transactional(readOnly = true)
	public boolean isUniqueByName(String oldName, String newName) {
		if (StringUtils.equalsIgnoreCase(oldName, newName)) {
			return true;
		}
		else {
			if (memberRankDao.isExistByName(newName)) {
				return false;
			}
			else {
				return true;
			}
		}
	}

	@Transactional(readOnly = true)
	public MemberRank load(String id) {
		return memberRankDao.get(id);
	}

	@Transactional(readOnly = true)
	public List<MemberRank> getAll() {
		return memberRankDao.getAll();
	}

	public void save(MemberRank memberRank) {
		memberRankDao.saveEntity(memberRank);
	}

	public void update(MemberRank memberRank) {
		memberRankDao.updateEntity(memberRank);
	}

	public void delete(MemberRank memberRank) {
		memberRankDao.delete(memberRank);
	}

	public void delete(String[] ids) {
		memberRankDao.delete(ids);
	}

	@Transactional(readOnly = true)
	public Pager findPager(Pager pager) {
		return memberRankDao.findPager(pager);
	}
}
