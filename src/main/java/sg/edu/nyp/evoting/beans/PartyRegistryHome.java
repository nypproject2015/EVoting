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
 * Home object for domain model class PartyRegistry.
 * @see sg.edu.nyp.evoting.beans.PartyRegistry
 * @author Hibernate Tools
 */
public class PartyRegistryHome {

	private static final Log log = LogFactory.getLog(PartyRegistryHome.class);

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

	public void persist(PartyRegistry transientInstance) {
		log.debug("persisting PartyRegistry instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PartyRegistry instance) {
		log.debug("attaching dirty PartyRegistry instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PartyRegistry instance) {
		log.debug("attaching clean PartyRegistry instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PartyRegistry persistentInstance) {
		log.debug("deleting PartyRegistry instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PartyRegistry merge(PartyRegistry detachedInstance) {
		log.debug("merging PartyRegistry instance");
		try {
			PartyRegistry result = (PartyRegistry) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PartyRegistry findById(java.lang.String id) {
		log.debug("getting PartyRegistry instance with id: " + id);
		try {
			PartyRegistry instance = (PartyRegistry) sessionFactory
					.getCurrentSession().get(
							"sg.edu.nyp.evoting.beans.PartyRegistry", id);
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

	public List<PartyRegistry> findByExample(PartyRegistry instance) {
		log.debug("finding PartyRegistry instance by example");
		try {
			List<PartyRegistry> results = (List<PartyRegistry>) sessionFactory
					.getCurrentSession()
					.createCriteria("sg.edu.nyp.evoting.beans.PartyRegistry")
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
