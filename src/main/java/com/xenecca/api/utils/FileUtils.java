package com.xenecca.api.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.xenecca.api.exception.FileStorageException;

public class FileUtils {
	private static final String UPLOAD_DIR = Paths.get("").toAbsolutePath().toString() + "/uploads/";

	public static String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (!new File(UPLOAD_DIR).exists()) {
			new File(UPLOAD_DIR).mkdir();
		}
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = Paths.get(UPLOAD_DIR + fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return "uploads/" + fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
		}

	}

	public static ByteArrayInputStream getFile(Path path) {
		try {
			byte[] fileContent = Files.readAllBytes(path);
			ByteArrayInputStream content = new ByteArrayInputStream(fileContent);
			return content;
		} catch (IOException e) {
			throw new FileStorageException("Could not fetch the file " + path.getFileName() + ". Please try again!");
		}

	}

	public static boolean deleteFile(Path path) {
		try {
			return Files.deleteIfExists(path);
		} catch (IOException e) {
			return false;
		}
	}

}
