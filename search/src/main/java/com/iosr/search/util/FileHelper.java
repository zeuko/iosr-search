package com.iosr.search.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Class contains static methods providing file access.
 *  
 * @author Patrycja
 */
public class FileHelper {
	
	/**
	 * Reads file from classpath resources.
	 * 
	 * @param filepath path to file to be read
	 * @return file file read from given path from resources
	 * @throws FileNotFoundException when file does not exist under selected path
	 */
	public static File getFileFromClasspath(String filepath) throws FileNotFoundException {
		Resource resource = new ClassPathResource(filepath);
		try {
			return resource.getFile();
		} catch (IOException e) {
			throw new FileNotFoundException(filepath);
		}
	}
}
