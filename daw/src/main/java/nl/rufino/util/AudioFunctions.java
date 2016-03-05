package nl.rufino.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AudioFunctions {
	
	private static final Logger LOGGER = LogManager.getLogger(AudioFunctions.class);
	
	public static File[] createNewTemplate(){
		LOGGER.entry();
		
		Properties propertiesFile = WindowsFunctions.retrieveProperties("config.properties");
		String searchDirectory = propertiesFile.getProperty("audio.searchdirectory");
		String templateDirectory = propertiesFile.getProperty("audio.templatedirectory");
		String templateYear = propertiesFile.getProperty("audio.templateyear");
		
		Integer newBeatNumber;
		String destDirName = "";
		File searchDir = new File(searchDirectory);
		File srcDir = new File(templateDirectory);
		File destDir;
		File cubaseTemplateFile;
		File cubaseNewBeateFile;
		File reasonTemplateFile;
		File reasonNewBeatFile;
		
		LOGGER.info("DAW template copying started");
		LOGGER.info("Determining where to copy the files to...");
		
		//Determine the highest track number
		newBeatNumber = determineNewBeatNumber(searchDir);
		
		LOGGER.info("Copying Cubase and Reason template files...");
		destDir = new File(searchDir + "\\Beat" + (newBeatNumber) + "_" + templateYear + "");
		
		//Copying template directory
		WindowsFunctions.copyDirectory(srcDir, destDir);

		//Hernoemen van bestanden
		cubaseTemplateFile = new File(destDir + "\\Cubase\\Template.cpr");
		cubaseNewBeateFile = new File(destDir + "\\Cubase\\" + "Beat" + newBeatNumber + "_" + templateYear + "_Cubase1.cpr");
		WindowsFunctions.renameFile(cubaseTemplateFile, cubaseNewBeateFile, newBeatNumber);
		
		reasonTemplateFile = new File(destDir + "\\Template.reason");
		reasonNewBeatFile = new File(destDir + "\\" + "Beat" + newBeatNumber + "_" + templateYear + "_Reason1.reason");
		WindowsFunctions.renameFile(reasonTemplateFile, reasonNewBeatFile, newBeatNumber);
		
		return new File[] {cubaseNewBeateFile, reasonNewBeatFile};
	}

	public static Integer determineNewBeatNumber(File searchDir) {
		Integer newBeatNumber = 1;
		String destDirName;
		File[] existingFolders = searchDir.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);

		Arrays.sort(existingFolders, Collections.reverseOrder());
		
		for (File dir : existingFolders) {
			destDirName = dir.getName();

			if(destDirName.startsWith("Beat")){
				int indexOfUnderscore = destDirName.indexOf("_");

				newBeatNumber = Integer.valueOf(destDirName.substring(4, indexOfUnderscore));

				newBeatNumber++;
				LOGGER.info("Highest beat number is: " + newBeatNumber);
				break;
			}
			System.out.println("Directory: " + dir.getName());
		}
		return newBeatNumber;
	}
	
	public static void addTrackNotes(File folderToScan){
		File[] existingFolders = folderToScan.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
		
		for (File file : existingFolders) {
			File dataFile = new File(file.getAbsolutePath() + "\\Gereserveerd.txt");
			String dataFileRead = WindowsFunctions.openFile(dataFile);
		}
	}
}
