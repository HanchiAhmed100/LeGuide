package com.dev.service;

import org.springframework.core.io.Resource;

import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
	  
	public void initStorage();

	public void saveCover(MultipartFile file);
	 
	public void savePdf(MultipartFile file);

	public void saveProfile(MultipartFile file);

	  
	public Resource load(String filename);

	public void deleteAll();


}
