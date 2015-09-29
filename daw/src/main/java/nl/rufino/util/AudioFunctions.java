package nl.rufino.util;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AudioFunctions {
	
	private static final Logger LOGGER = LogManager.getLogger(AudioFunctions.class);
	
	public static File[] createNewTemplate(){
		LOGGER.entry();
		Integer newBeatNumber = 0;
		String destDirName = "";
		File searchDir = new File("Z:\\Filmic Music\\Muziek\\Beat 2015");
		File srcDir = new File("Z:\\Filmic Music\\Muziek\\Beat 2015\\_Template");
		File destDir;
		File cubaseTemplateFile;
		File cubaseNewBeateFile;
		File reasonTemplateFile;
		File reasonNewBeatFile;
		
		LOGGER.info("DAW template copying started");
		LOGGER.info("Determining where to copy the files to...");
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
		LOGGER.info("Copying Cubase and Reason template files...");
		destDir = new File(searchDir + "\\Beat" + (newBeatNumber) + "_2015");
		
		//Kopiëren van de template directory
		WindowsFunctions.copyDirectory(srcDir, destDir);

		//Hernoemen van bestanden
		cubaseTemplateFile = new File(destDir + "\\Cubase\\Template.cpr");
		cubaseNewBeateFile = new File(destDir + "\\Cubase\\" + "Beat" + newBeatNumber + "_2015_Cubase1.cpr");
		WindowsFunctions.renameFile(cubaseTemplateFile, cubaseNewBeateFile, newBeatNumber);
		
		reasonTemplateFile = new File(destDir + "\\Template.reason");
		reasonNewBeatFile = new File(destDir + "\\" + "Beat" + newBeatNumber + "_2015_Reason1.reason");
		WindowsFunctions.renameFile(reasonTemplateFile, reasonNewBeatFile, newBeatNumber);
		
		return new File[] {cubaseNewBeateFile, reasonNewBeatFile};
	}
	
	public static void addTrackNotes(File folderToScan){
		File[] existingFolders = folderToScan.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
		
		for (File file : existingFolders) {
			File dataFile = new File(file.getAbsolutePath() + "\\Gereserveerd.txt");
			String dataFileRead = WindowsFunctions.openFile(dataFile);
		}
	}
}
