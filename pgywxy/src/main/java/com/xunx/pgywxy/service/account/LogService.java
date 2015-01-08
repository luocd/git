package com.xunx.pgywxy.service.account;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunx.pgywxy.bean.Pager;
import com.xunx.pgywxy.dao.account.LogDao;
import com.xunx.pgywxy.entity.account.Log;

/**
 * ClassName: LogService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2012-8-21 上午11:37:06 <br/>
 *
 * @version 
 * @since JDK 1.6
 */
@Service("logService")
public class LogService {

	@Resource(name = "logDao")
	private LogDao logDao;

	@Transactional
	public String save(Log log) {
		return logDao.saveEntity(log);
	}

	@Transactional(readOnly = true)
	public Log load(String id) {
		return logDao.get(id);
	}

	@Transactional
	public void delete(Log log) {
		logDao.delete(log);
	}

	@Transactional
	public void delete(String[] ids) {
		logDao.delete(ids);
	}

	@Transactional(readOnly = true)
	public Pager findPager(Pager pager) {
		return logDao.findPager(pager);
	}
}
