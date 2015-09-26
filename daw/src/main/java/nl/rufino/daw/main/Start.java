package nl.rufino.daw.main;


import java.io.File;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import nl.rufino.daw.util.AudioFunctions;
import nl.rufino.daw.util.WindowsFunctions;
import nl.rufino.daw.view.DawMainView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Start {
	static final Logger LOGGER = LogManager.getLogger(Start.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DawMainView(args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw_data");
		EntityManager em = emf.createEntityManager();
		
		File folderToScan = new File("Z:\\Filmic Music\\Muziek\\Beat 2015");
		AudioFunctions.addTrackNotes(folderToScan);
	}

}
