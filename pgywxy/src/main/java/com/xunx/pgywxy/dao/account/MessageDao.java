package com.xunx.pgywxy.dao.account;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.xunx.pgywxy.bean.Pager;
import com.xunx.pgywxy.dao.BaseDao;
import com.xunx.pgywxy.entity.account.Admin;
import com.xunx.pgywxy.entity.account.Message;
import com.xunx.pgywxy.entity.account.Message.DeleteStatus;

/**
 * ClassName:MessageDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2012-8-21 上午10:37:12 <br/>
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Repository("messageDao")
public class MessageDao extends BaseDao<Message, String> {

	/**
	 * 
	 * getMemberInboxPager:根据Member、Pager获取会员收件箱分页对象. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author JasonZhang
	 * @param member
	 * @param pager
	 * @return
	 * @since JDK 1.6
	 */
	public Pager getMemberInboxPager(Admin member, Pager pager) {
		return super.findPager(pager, Restrictions.eq("toMember", member),
				Restrictions.eq("isSaveDraftbox", false),
				Restrictions.ne("deleteStatus", DeleteStatus.toDelete));
	}

	/**
	 * 
	 * getMemberOutboxPager:根据Member、Pager获取会员发件箱分页对象. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author JasonZhang
	 * @param member
	 * @param pager
	 * @return
	 * @since JDK 1.6
	 */
	public Pager getMemberOutboxPager(Admin member, Pager pager) {
		return super.findPager(pager, Restrictions.eq("fromMember", member),
				Restrictions.eq("isSaveDraftbox", false),
				Restrictions.ne("deleteStatus", DeleteStatus.fromDelete));
	}

	/**
	 * 
	 * getMemberDraftboxPager:根据Member、Pager获取会员草稿箱分页对象. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author JasonZhang
	 * @param member
	 * @param pager
	 * @return
	 * @since JDK 1.6
	 */
	public Pager getMemberDraftboxPager(Admin member, Pager pager) {
		return super.findPager(pager, Restrictions.eq("fromMember", member),
				Restrictions.eq("isSaveDraftbox", true),
				Restrictions.ne("deleteStatus", DeleteStatus.fromDelete));
	}

	/**
	 * 
	 * getAdminInboxPager:根据Pager获取管理员收件箱消息分页对象. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author JasonZhang
	 * @param pager
	 * @return
	 * @since JDK 1.6
	 */
	public Pager getAdminInboxPager(Pager pager) {
		return super.findPager(pager, Restrictions.isNull("toMember"),
				Restrictions.eq("isSaveDraftbox", false),
				Restrictions.ne("deleteStatus", DeleteStatus.toDelete));
	}

	/**
	 * 
	 * getAdminOutboxPager:根据Pager获取管理员发件箱消息分页对象. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author JasonZhang
	 * @param pager
	 * @return
	 * @since JDK 1.6
	 */
	public Pager getAdminOutboxPager(Pager pager) {
		return super.findPager(pager, Restrictions.isNull("fromMember"),
				Restrictions.eq("isSaveDraftbox", false),
				Restrictions.ne("deleteStatus", DeleteStatus.fromDelete));
	}

	/**
	 * 
	 * getUnreadMessageCount:根据Member获取未读消息数. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author JasonZhang
	 * @param member
	 * @return
	 * @since JDK 1.6
	 */
	public Long getUnreadMessageCount(Admin member) {
		String hql = "select count(*) from Message as message where message.toMember = :toMember and message.isRead = :isRead and message.isSaveDraftbox = :isSaveDraftbox and message.deleteStatus != :deleteStatus";
		return (Long) getSession().createQuery(hql).setParameter("toMember", member)
				.setParameter("isRead", false).setParameter("isSaveDraftbox", false)
				.setParameter("deleteStatus", DeleteStatus.toDelete).uniqueResult();
	}

	/**
	 * 
	 * getUnreadMessageCount:获取管理员未读消息数. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author JasonZhang
	 * @return
	 * @since JDK 1.6
	 */
	public Long getUnreadMessageCount() {
		String hql = "select count(*) from Message as message where message.toMember is null and message.isRead = :isRead and message.isSaveDraftbox = :isSaveDraftbox and message.deleteStatus != :deleteStatus";
		return (Long) getSession().createQuery(hql).setParameter("isRead", false)
				.setParameter("isSaveDraftbox", false)
				.setParameter("deleteStatus", DeleteStatus.toDelete).uniqueResult();
	}
}
