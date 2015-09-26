package nl.rufino.daw.data.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MusicEntityTest {
	private EntityManager em;
	private EntityManagerFactory emf;

	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("daw_data");
		em = emf.createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Tearing down");
		em.close();
		emf.close();
	}

	@Test
	public void testCheckDatabaseConnection() {
		EntityTransaction transaction = em.getTransaction();
		
		Query queryFindAll = em.createNamedQuery("MusicEntity.findAll");
		Collection<MusicEntity> result = null;
		assertNull(result);
		
		result = queryFindAll.getResultList();
		
		assertNotNull(result);
	}

}
