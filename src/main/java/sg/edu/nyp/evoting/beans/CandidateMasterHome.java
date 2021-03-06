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
 * Home object for domain model class CandidateMaster.
 * @see sg.edu.nyp.evoting.beans.CandidateMaster
 * @author Hibernate Tools
 */
public class CandidateMasterHome {

	private static final Log log = LogFactory.getLog(CandidateMasterHome.class);

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

	public void persist(CandidateMaster transientInstance) {
		log.debug("persisting CandidateMaster instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CandidateMaster instance) {
		log.debug("attaching dirty CandidateMaster instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CandidateMaster instance) {
		log.debug("attaching clean CandidateMaster instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CandidateMaster persistentInstance) {
		log.debug("deleting CandidateMaster instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CandidateMaster merge(CandidateMaster detachedInstance) {
		log.debug("merging CandidateMaster instance");
		try {
			CandidateMaster result = (CandidateMaster) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CandidateMaster findById(int id) {
		log.debug("getting CandidateMaster instance with id: " + id);
		try {
			CandidateMaster instance = (CandidateMaster) sessionFactory
					.getCurrentSession().get(
							"sg.edu.nyp.evoting.beans.CandidateMaster", id);
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

	public List<CandidateMaster> findByExample(CandidateMaster instance) {
		log.debug("finding CandidateMaster instance by example");
		try {
			List<CandidateMaster> results = (List<CandidateMaster>) sessionFactory
					.getCurrentSession()
					.createCriteria("sg.edu.nyp.evoting.beans.CandidateMaster")
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
