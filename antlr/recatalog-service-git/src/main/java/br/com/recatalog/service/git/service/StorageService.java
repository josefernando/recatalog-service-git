package br.com.recatalog.service.git.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
	
	private Logger logger = LoggerFactory.getLogger(StorageService.class);
	
	private final  Path rootLocation = Paths.get("upload-dir");
	
	public void store(MultipartFile file) {
		try {
			
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			logger.error("Error on store MultipartFile: " + e.getMessage());
			throw new RuntimeException("FAIL!");
		}
	}
	
	public void storeFolder(MultipartFile file) {
		try {
			Path path = Paths.get(file.getOriginalFilename());
			
			Path pp = Paths.get(path.subpath(0, path.getNameCount()-1).toString());
			
			Path px = rootLocation.resolve(pp);
			
			if(!Files.exists(px)) {
				Files.createDirectories(px);
			}
			
			Path target = px.resolve(path.getFileName().toString());
			
			Files.copy(file.getInputStream(), target);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error on store MultipartFile: " + e.getMessage());
			throw new RuntimeException("FAIL!");
		}
	}
	
	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");				
			}
		} catch(MalformedURLException e) {
			logger.error("Error: " + e.getMessage());
			throw new RuntimeException("FAIL!");
		}
	}
	
	public void deleteAll() {
		try {
			FileSystemUtils.deleteRecursively(rootLocation);
		} catch (IOException e) {
			logger.error("Error: " + e.getMessage());
			throw new RuntimeException("FAIL!");
		}
	}
	
	public void init() {
			try {
				Files.createDirectory(rootLocation);
			} catch (IOException e) {
				logger.error("Error: " + e.getMessage());
				throw new RuntimeException("Coud not initilize storage!");
			}
	}
}