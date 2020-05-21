package br.com.recatalog.service.git;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.recatalog.service.git.service.StorageService;

@SpringBootApplication
public class RecatalogServiceGitApplication{
	
	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(RecatalogServiceGitApplication.class, args);
	}

	@PostConstruct
	void init() {
		storageService.deleteAll();
		storageService.init();
	}
}