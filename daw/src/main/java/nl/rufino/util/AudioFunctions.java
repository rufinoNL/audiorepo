package nl.rufino.util;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AudioFunctions {
	
	static final Logger LOGGER = LoggerFactory.getLogger(AudioFunctions.class);
	
	public static File[] createNewTemplate(String searchDirectory, String templateDirectory, String templateYear){
		LOGGER.info("Started");
				
		String newBeatNumber;
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
		WindowsFunctions.renameFile(cubaseTemplateFile, cubaseNewBeateFile);
		
		reasonTemplateFile = new File(destDir + "\\Template.reason");
		reasonNewBeatFile = new File(destDir + "\\" + "Beat" + newBeatNumber + "_" + templateYear + "_Reason1.reason");
		WindowsFunctions.renameFile(reasonTemplateFile, reasonNewBeatFile);
		
		return new File[] {cubaseNewBeateFile, reasonNewBeatFile};
	}

	public static String determineNewBeatNumber(File searchDir) {
		Integer newBeatNumber = determineHighestBeatFolder(searchDir);
		StringBuffer newBeatNummer = addLeadingZeros(newBeatNumber);
		return newBeatNummer.toString();
	}

	public static Integer determineHighestBeatFolder(File searchDir) {
		File[] existingFolders = searchDir.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
		Integer beatNumber = 1;
		Arrays.sort(existingFolders, Collections.reverseOrder());
		
		for (File dir : existingFolders) {
			String destDirName;
			destDirName = dir.getName();

			if(destDirName.startsWith("Beat")){
				int indexOfUnderscore = destDirName.indexOf("_");

				beatNumber = Integer.valueOf(destDirName.substring(4, indexOfUnderscore));

				beatNumber++;
				LOGGER.info("Highest beat number is: " + beatNumber);
				break;
			}
			LOGGER.info("Directory: " + dir.getName());
		}
		return beatNumber;
	}

	public static StringBuffer addLeadingZeros(Integer number) {
		StringBuffer newBeatNummer = new StringBuffer();
		if(number < 10){
			newBeatNummer.append("0");
		}
		newBeatNummer.append(number.toString());
		return newBeatNummer;
	}
	
	public static void addTrackNotes(File folderToScan){
		File[] existingFolders = folderToScan.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
		
		for (File file : existingFolders) {
			File dataFile = new File(file.getAbsolutePath() + "\\Gereserveerd.txt");
			String dataFileRead = WindowsFunctions.openFile(dataFile);
		}
	}
}
