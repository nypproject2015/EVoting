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
 * Home object for domain model class VoterRegistry.
 * @see sg.edu.nyp.evoting.beans.VoterRegistry
 * @author Hibernate Tools
 */
public class VoterRegistryHome {

	private static final Log log = LogFactory.getLog(VoterRegistryHome.class);

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

	public void persist(VoterRegistry transientInstance) {
		log.debug("persisting VoterRegistry instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VoterRegistry instance) {
		log.debug("attaching dirty VoterRegistry instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VoterRegistry instance) {
		log.debug("attaching clean VoterRegistry instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VoterRegistry persistentInstance) {
		log.debug("deleting VoterRegistry instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VoterRegistry merge(VoterRegistry detachedInstance) {
		log.debug("merging VoterRegistry instance");
		try {
			VoterRegistry result = (VoterRegistry) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VoterRegistry findById(java.lang.String id) {
		log.debug("getting VoterRegistry instance with id: " + id);
		try {
			VoterRegistry instance = (VoterRegistry) sessionFactory
					.getCurrentSession().get(
							"sg.edu.nyp.evoting.beans.VoterRegistry", id);
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

	public List<VoterRegistry> findByExample(VoterRegistry instance) {
		log.debug("finding VoterRegistry instance by example");
		try {
			List<VoterRegistry> results = (List<VoterRegistry>) sessionFactory
					.getCurrentSession()
					.createCriteria("sg.edu.nyp.evoting.beans.VoterRegistry")
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
