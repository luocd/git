package com.xunx.pgywxy.service.account;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunx.pgywxy.bean.Pager;
import com.xunx.pgywxy.dao.account.MessageDao;
import com.xunx.pgywxy.entity.account.Admin;
import com.xunx.pgywxy.entity.account.Message;

/**
 * ClassName: MessageService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2012-8-21 上午10:38:36 <br/>
 *
 * @version 
 * @since JDK 1.6
 */
@Service("messageService")
public class MessageService {

	@Resource(name = "messageDao")
	private MessageDao messageDao;

	@Transactional
	public String save(Message message) {
		return messageDao.saveEntity(message);
	}

	@Transactional
	public void update(Message message) {
		messageDao.updateEntity(message);
	}

	@Transactional(readOnly = true)
	public Message load(String id) {
		return messageDao.get(id);
	}

	@Transactional
	public void delete(Message message) {
		messageDao.delete(message);
	}

	@Transactional(readOnly = true)
	public Pager findPager(Pager pager) {
		return messageDao.findPager(pager);
	}

	@Transactional(readOnly = true)
	public Long getTotalCount() {
		return messageDao.getTotalCount();
	}

	@Transactional(readOnly = true)
	public Pager getMemberInboxPager(Admin member, Pager pager) {
		return messageDao.getMemberInboxPager(member, pager);
	}

	@Transactional(readOnly = true)
	public Pager getMemberOutboxPager(Admin member, Pager pager) {
		return messageDao.getMemberOutboxPager(member, pager);
	}

	@Transactional(readOnly = true)
	public Pager getMemberDraftboxPager(Admin member, Pager pager) {
		return messageDao.getMemberDraftboxPager(member, pager);
	}

	@Transactional(readOnly = true)
	public Pager getAdminInboxPager(Pager pager) {
		return messageDao.getAdminInboxPager(pager);
	}

	@Transactional(readOnly = true)
	public Pager getAdminOutboxPager(Pager pager) {
		return messageDao.getAdminOutboxPager(pager);
	}

	@Transactional(readOnly = true)
	public Long getUnreadMessageCount(Admin member) {
		return messageDao.getUnreadMessageCount(member);
	}

	@Transactional(readOnly = true)
	public Long getUnreadMessageCount() {
		return messageDao.getUnreadMessageCount();
	}
}
