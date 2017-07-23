package com.easyop.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.easyop.model.Tnews;

/**
 * A data access object (DAO) providing persistence and search support for Tnews
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.easyop.dao.Tnews
 * @author MyEclipse Persistence Tools
 */

public class TnewsDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TnewsDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String TITSHLDER = "titshlder";
	public static final String TITSEC = "titsec";
	public static final String SOURCE = "source";
	public static final String WRITER = "writer";
	public static final String PIC_URL = "picUrl";
	public static final String ABSTRACT_ = "abstract_";
	public static final String CONTENT = "content";
	public static final String MAKER_ID = "makerId";
	public static final String MAKEINFO = "makeinfo";
	public static final String CHECKR_ID = "checkrId";
	public static final String CHECKRES = "checkres";
	public static final String CHECKINFO = "checkinfo";
	public static final String PUBER_ID = "puberId";
	public static final String RANK_ID = "rankId";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	public void save(Tnews transientInstance) {
		log.debug("saving Tnews instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tnews persistentInstance) {
		log.debug("deleting Tnews instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tnews findById(java.lang.Long id) {
		log.debug("getting Tnews instance with id: " + id);
		try {
			Tnews instance = (Tnews) getHibernateTemplate().get(
					"com.easyop.model.Tnews", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tnews instance) {
		log.debug("finding Tnews instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Tnews instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tnews as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByTitshlder(Object titshlder) {
		return findByProperty(TITSHLDER, titshlder);
	}

	public List findByTitsec(Object titsec) {
		return findByProperty(TITSEC, titsec);
	}

	public List findBySource(Object source) {
		return findByProperty(SOURCE, source);
	}

	public List findByWriter(Object writer) {
		return findByProperty(WRITER, writer);
	}

	public List findByPicUrl(Object picUrl) {
		return findByProperty(PIC_URL, picUrl);
	}

	public List findByAbstract_(Object abstract_) {
		return findByProperty(ABSTRACT_, abstract_);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByMakerId(Object makerId) {
		return findByProperty(MAKER_ID, makerId);
	}

	public List findByMakeinfo(Object makeinfo) {
		return findByProperty(MAKEINFO, makeinfo);
	}

	public List findByCheckrId(Object checkrId) {
		return findByProperty(CHECKR_ID, checkrId);
	}

	public List findByCheckres(Object checkres) {
		return findByProperty(CHECKRES, checkres);
	}

	public List findByCheckinfo(Object checkinfo) {
		return findByProperty(CHECKINFO, checkinfo);
	}

	public List findByPuberId(Object puberId) {
		return findByProperty(PUBER_ID, puberId);
	}

	public List findByRankId(Object rankId) {
		return findByProperty(RANK_ID, rankId);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all Tnews instances");
		try {
			String queryString = "from Tnews";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tnews merge(Tnews detachedInstance) {
		log.debug("merging Tnews instance");
		try {
			Tnews result = (Tnews) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tnews instance) {
		log.debug("attaching dirty Tnews instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tnews instance) {
		log.debug("attaching clean Tnews instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TnewsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TnewsDAO) ctx.getBean("TnewsDAO");
	}
}