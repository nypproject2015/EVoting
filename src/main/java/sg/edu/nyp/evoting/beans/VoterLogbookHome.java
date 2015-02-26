package sg.edu.nyp.evoting.beans;

// Generated Feb 9, 2015 1:01:14 PM by Hibernate Tools 3.4.0.CR1

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
 * Home object for domain model class VoterLogbook.
 * @see sg.edu.nyp.evoting.beans.VoterLogbook
 * @author Hibernate Tools
 */
public class VoterLogbookHome {

	private static final Log log = LogFactory.getLog(VoterLogbookHome.class);

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

	public void persist(VoterLogbook transientInstance) {
		log.debug("persisting VoterLogbook instance");
		try {
			openCurrentSessionwithTransaction();
			currentSession.persist(transientInstance);
			log.debug("persist successful");
			closeCurrentSessionwithTransaction();
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VoterLogbook instance) {
		log.debug("attaching dirty VoterLogbook instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VoterLogbook instance) {
		log.debug("attaching clean VoterLogbook instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VoterLogbook persistentInstance) {
		log.debug("deleting VoterLogbook instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VoterLogbook merge(VoterLogbook detachedInstance) {
		log.debug("merging VoterLogbook instance");
		try {
			VoterLogbook result = (VoterLogbook) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VoterLogbook findById(sg.edu.nyp.evoting.beans.VoterLogbookId id) {
		log.debug("getting VoterLogbook instance with id: " + id);
		try {
			VoterLogbook instance = (VoterLogbook) sessionFactory
					.getCurrentSession().get(
							"sg.edu.nyp.evoting.beans.VoterLogbook", id);
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

	public List<VoterLogbook> findByExample(VoterLogbook instance) {
		log.debug("finding VoterLogbook instance by example");
		try {			
			List<VoterLogbook> results = (List<VoterLogbook>) currentSession.createCriteria("sg.edu.nyp.evoting.beans.VoterLogbook").add(create(instance)).list();
			log.debug("find by example successful, result size: "	+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
