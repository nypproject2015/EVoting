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
 * Home object for domain model class ElectionMaster.
 * @see sg.edu.nyp.evoting.beans.ElectionMaster
 * @author Hibernate Tools
 */
public class ElectionMasterHome {

	private static final Log log = LogFactory.getLog(ElectionMasterHome.class);

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

	public void persist(ElectionMaster transientInstance) {
		log.debug("persisting ElectionMaster instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ElectionMaster instance) {
		log.debug("attaching dirty ElectionMaster instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ElectionMaster instance) {
		log.debug("attaching clean ElectionMaster instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ElectionMaster persistentInstance) {
		log.debug("deleting ElectionMaster instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ElectionMaster merge(ElectionMaster detachedInstance) {
		log.debug("merging ElectionMaster instance");
		try {
			ElectionMaster result = (ElectionMaster) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ElectionMaster findById(int id) {
		log.debug("getting ElectionMaster instance with id: " + id);
		try {
			ElectionMaster instance = (ElectionMaster) sessionFactory
					.getCurrentSession().get(
							"sg.edu.nyp.evoting.beans.ElectionMaster", id);
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

	public List<ElectionMaster> findByExample(ElectionMaster instance) {
		log.debug("finding ElectionMaster instance by example");
		try {
			List<ElectionMaster> results = (List<ElectionMaster>) sessionFactory
					.getCurrentSession()
					.createCriteria("sg.edu.nyp.evoting.beans.ElectionMaster")
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
