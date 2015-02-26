package sg.edu.nyp.evoting.beans;

// Generated Feb 7, 2015 7:06:49 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class PollingLog.
 * @see sg.edu.nyp.evoting.beans.PollingLog
 * @author Hibernate Tools
 */
public class PollingLogHome {

	private static final Log log = LogFactory.getLog(PollingLogHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(PollingLog transientInstance) {
		log.debug("persisting PollingLog instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PollingLog instance) {
		log.debug("attaching dirty PollingLog instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PollingLog instance) {
		log.debug("attaching clean PollingLog instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PollingLog persistentInstance) {
		log.debug("deleting PollingLog instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PollingLog merge(PollingLog detachedInstance) {
		log.debug("merging PollingLog instance");
		try {
			PollingLog result = (PollingLog) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PollingLog findById(sg.edu.nyp.evoting.beans.PollingLogId id) {
		log.debug("getting PollingLog instance with id: " + id);
		try {
			PollingLog instance = (PollingLog) sessionFactory
					.getCurrentSession().get(
							"sg.edu.nyp.evoting.beans.PollingLog", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<PollingLog> findByExample(PollingLog instance) {
		log.debug("finding PollingLog instance by example");
		try {
			List<PollingLog> results = (List<PollingLog>) sessionFactory
					.getCurrentSession()
					.createCriteria("sg.edu.nyp.evoting.beans.PollingLog")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
