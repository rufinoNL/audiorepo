package nl.rufino.daw.data.entity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
	public void test() {		
		Query queryFindAll = em.createNamedQuery("MusicEntity.findAll");
		Collection<MusicEntity> result = queryFindAll.getResultList();
		
		assertNotNull(result);
	}

}
