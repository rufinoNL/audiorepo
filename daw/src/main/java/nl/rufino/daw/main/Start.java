package nl.rufino.daw.main;


import java.io.File;
import java.io.IOException;

import nl.rufino.daw.util.AudioFunctions;
import nl.rufino.daw.util.WindowsFunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Start {
	static final Logger LOGGER = LogManager.getLogger(Start.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		File folderToScan = new File("Z:\\Filmic Music\\Muziek\\Beat 2015");
		AudioFunctions.analyzeAudioGenres(folderToScan);
		
//		File[] fileToStart = AudioFunctions.createNewTemplate();
//		
//		//Argumenten uitlezen
//		if(args.length > 0){
//			for (String s : args) {
//				if("Analyze".equals(s)){
//					//Start loop en ga door zo lang er mappen zijn van een bepaald jaar
//					//Ga naar de eerste map
//					
//				}
//			}
//		}else{
//			try {
//				WindowsFunctions.startApplication("C:\\Program Files\\Steinberg\\Cubase 6\\Cubase6.exe", 
//						fileToStart[0].toString());
//				
//				//25 s wachten voordat Reason wordt gestart
//				Thread.sleep(25000);
//				
//				WindowsFunctions.startApplication("C:\\Program Files\\Propellerhead\\Reason\\Reason.exe", 
//						fileToStart[1].toString());
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

}
