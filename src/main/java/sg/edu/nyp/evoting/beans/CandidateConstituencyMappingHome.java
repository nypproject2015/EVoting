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
 * Home object for domain model class CandidateConstituencyMapping.
 * @see sg.edu.nyp.evoting.beans.CandidateConstituencyMapping
 * @author Hibernate Tools
 */
public class CandidateConstituencyMappingHome {

	private static final Log log = LogFactory
			.getLog(CandidateConstituencyMappingHome.class);

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

	public void persist(CandidateConstituencyMapping transientInstance) {
		log.debug("persisting CandidateConstituencyMapping instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CandidateConstituencyMapping instance) {
		log.debug("attaching dirty CandidateConstituencyMapping instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CandidateConstituencyMapping instance) {
		log.debug("attaching clean CandidateConstituencyMapping instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CandidateConstituencyMapping persistentInstance) {
		log.debug("deleting CandidateConstituencyMapping instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CandidateConstituencyMapping merge(
			CandidateConstituencyMapping detachedInstance) {
		log.debug("merging CandidateConstituencyMapping instance");
		try {
			CandidateConstituencyMapping result = (CandidateConstituencyMapping) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CandidateConstituencyMapping findById(java.lang.String id) {
		log.debug("getting CandidateConstituencyMapping instance with id: "
				+ id);
		try {
			CandidateConstituencyMapping instance = (CandidateConstituencyMapping) sessionFactory
					.getCurrentSession()
					.get("sg.edu.nyp.evoting.beans.CandidateConstituencyMapping",
							id);
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

	public List<CandidateConstituencyMapping> findByExample(
			CandidateConstituencyMapping instance) {
		log.debug("finding CandidateConstituencyMapping instance by example");
		try {
			List<CandidateConstituencyMapping> results = (List<CandidateConstituencyMapping>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"sg.edu.nyp.evoting.beans.CandidateConstituencyMapping")
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
