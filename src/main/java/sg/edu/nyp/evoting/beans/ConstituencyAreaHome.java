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
 * Home object for domain model class ConstituencyArea.
 * @see sg.edu.nyp.evoting.beans.ConstituencyArea
 * @author Hibernate Tools
 */
public class ConstituencyAreaHome {

	private static final Log log = LogFactory
			.getLog(ConstituencyAreaHome.class);

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

	public void persist(ConstituencyArea transientInstance) {
		log.debug("persisting ConstituencyArea instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ConstituencyArea instance) {
		log.debug("attaching dirty ConstituencyArea instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConstituencyArea instance) {
		log.debug("attaching clean ConstituencyArea instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ConstituencyArea persistentInstance) {
		log.debug("deleting ConstituencyArea instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConstituencyArea merge(ConstituencyArea detachedInstance) {
		log.debug("merging ConstituencyArea instance");
		try {
			ConstituencyArea result = (ConstituencyArea) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ConstituencyArea findById(
			sg.edu.nyp.evoting.beans.ConstituencyAreaId id) {
		log.debug("getting ConstituencyArea instance with id: " + id);
		try {
			ConstituencyArea instance = (ConstituencyArea) sessionFactory
					.getCurrentSession().get(
							"sg.edu.nyp.evoting.beans.ConstituencyArea", id);
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

	public List<ConstituencyArea> findByExample(ConstituencyArea instance) {
		log.debug("finding ConstituencyArea instance by example");
		try {
			List<ConstituencyArea> results = (List<ConstituencyArea>) sessionFactory
					.getCurrentSession()
					.createCriteria("sg.edu.nyp.evoting.beans.ConstituencyArea")
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
