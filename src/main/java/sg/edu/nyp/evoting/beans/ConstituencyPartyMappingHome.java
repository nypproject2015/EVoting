package sg.edu.nyp.evoting.beans;

// Generated Feb 7, 2015 7:06:49 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * Home object for domain model class ConstituencyPartyMapping.
 * @see sg.edu.nyp.evoting.beans.ConstituencyPartyMapping
 * @author Hibernate Tools
 */
public class ConstituencyPartyMappingHome {

	private static final Log log = LogFactory
			.getLog(ConstituencyPartyMappingHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	private Session currentSession;

    private Transaction currentTransaction;
	public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
       currentTransaction.commit();
       currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        //Configuration configuration = new Configuration().configure();
        //StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        //SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());		
        //return sessionFactory;
		
		return new Configuration().configure().buildSessionFactory();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

	public void persist(ConstituencyPartyMapping transientInstance) {
		log.debug("persisting ConstituencyPartyMapping instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ConstituencyPartyMapping instance) {
		log.debug("attaching dirty ConstituencyPartyMapping instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConstituencyPartyMapping instance) {
		log.debug("attaching clean ConstituencyPartyMapping instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ConstituencyPartyMapping persistentInstance) {
		log.debug("deleting ConstituencyPartyMapping instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConstituencyPartyMapping merge(
			ConstituencyPartyMapping detachedInstance) {
		log.debug("merging ConstituencyPartyMapping instance");
		try {
			ConstituencyPartyMapping result = (ConstituencyPartyMapping) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ConstituencyPartyMapping findById(
			sg.edu.nyp.evoting.beans.ConstituencyPartyMappingId id) {
		log.debug("getting ConstituencyPartyMapping instance with id: " + id);
		try {
			ConstituencyPartyMapping instance = (ConstituencyPartyMapping) sessionFactory
					.getCurrentSession()
					.get("sg.edu.nyp.evoting.beans.ConstituencyPartyMapping",
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

	public List<ConstituencyPartyMapping> findByExample(
			ConstituencyPartyMapping instance) {
		log.debug("finding ConstituencyPartyMapping instance by example");
		try {
			List<ConstituencyPartyMapping> results = (List<ConstituencyPartyMapping>) currentSession.createCriteria(						"sg.edu.nyp.evoting.beans.ConstituencyPartyMapping").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
