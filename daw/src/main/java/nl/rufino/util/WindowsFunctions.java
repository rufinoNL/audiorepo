package nl.rufino.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WindowsFunctions {
	
	static final Logger LOGGER = LogManager.getLogger(WindowsFunctions.class);
	
	public static void renameFile(File oldFileName, File newFileName) {
		oldFileName.renameTo(newFileName);
		LOGGER.info("Renamed file: " + oldFileName.toString() + " to " + newFileName.toString());
	}

	public static void copyDirectory(File sourceDirectory, File destinationDirectory) {
		try {
			FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
			LOGGER.info("Template copying complete");
		} catch (IOException e) {
			LOGGER.error("Problems copying file: " + e);
		}
	}
	
	public static String openFile(File fileToOpen){
		String fileRead = null;
		try {
			fileRead = FileUtils.readFileToString(fileToOpen);
			LOGGER.info("File " + fileToOpen.getName() + " has been read.");

		} catch (IOException e) {
			LOGGER.error("Problems opening file: " + e);
		}
		
		return fileRead;
	}
	
	public static void startApplication(String executablePath, String fileToOpen) throws IOException{
		//Start program
		new ProcessBuilder(executablePath, fileToOpen).start();
	}
	
	public static Properties retrieveProperties(String fileName){
		Properties properties = new Properties();
		
		InputStream inputStream = AudioFunctions.class.getClassLoader().getResourceAsStream(fileName);
		
		if(inputStream != null){
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}else{
			LOGGER.warn("File not found");
		}
		
		return properties;
	}
}
