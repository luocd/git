package com.xunx.pgywxy.dao.member;

import org.springframework.stereotype.Repository;

import com.xunx.pgywxy.dao.BaseDao;
import com.xunx.pgywxy.entity.member.MemberRank;

/**
 * ClassName:MemberRankDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2012-8-21 ����11:35:31 <br/>
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Repository("memberRankDao")
public class MemberRankDao extends BaseDao<MemberRank, String> {
	public boolean isExistByName(String name) {
		String hql = "from MemberRank as memberRank where lower(memberRank.name) = lower(:name)";
		MemberRank memberRank = (MemberRank) getSession().createQuery(hql)
				.setParameter("name", name).uniqueResult();
		if (memberRank != null) {
			return true;
		}
		else {
			return false;
		}
	}
}
