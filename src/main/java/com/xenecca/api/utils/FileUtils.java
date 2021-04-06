package com.xenecca.api.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
	
	public static boolean deleteFile(Path path) {
		try {
		    return Files.deleteIfExists(path);
		} catch (IOException e) {
		    return false;
		}
	}
}
