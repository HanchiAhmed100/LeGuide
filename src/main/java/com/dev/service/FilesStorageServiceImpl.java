package com.dev.service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

	  private final Path rootCovers = Paths.get("src/main/resources/static/uploads/images/covers");
	  
	  private final Path rootPdf = Paths.get("src/main/resources/static/uploads/pdf");

	  private final Path rootProfile = Paths.get("src/main/resources/static/uploads/images/profile");

	  @Override
	  public void initStorage() {
	    try {
	      Files.createDirectory(rootCovers);
	      Files.createDirectory(rootPdf);
	      Files.createDirectories(rootProfile);
	    } catch (IOException e) {
	    	e.printStackTrace();
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }
	  
	  @Override
	  public void saveCover(MultipartFile file) {
	    try {
	    	Files.copy(file.getInputStream(), this.rootCovers.resolve(file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
	    } catch (Exception e) {
	    	e.printStackTrace();
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }
	  @Override
	  public void saveProfile(MultipartFile file) {
	    try {
	    	Files.copy(file.getInputStream(), this.rootProfile.resolve(file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
	    } catch (Exception e) {
	    	e.printStackTrace();
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }
	  
	  @Override
	  public void savePdf(MultipartFile file) {
		  System.out.println("save");
	    try {
    		Files.copy(file.getInputStream(), this.rootPdf.resolve(file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);		
	    } catch (Exception e) {
	    	e.printStackTrace();

	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }
	  
	  @Override
	  public Resource load(String filename) {
	    try {
	      Path file = rootCovers.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());
	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	  @Override
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(rootCovers.toFile());
	  }
	  
}
