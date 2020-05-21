package br.com.recatalog.service.git.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.recatalog.util.PropertyList;

@Service
public class GitRepoService {
	
	@Value("${repo.path.base}")
	private String repoPathBase;
	
	private Logger logger = LoggerFactory.getLogger(GitRepoService.class);
	
	public PropertyList init(String repoName) {
		PropertyList propertyList = new PropertyList();
		
		String repoPath = repoPathBase + System.getProperty("file.separator") + repoName;
		
		if(open(repoName) != null) {
			propertyList.addProperty("ERROR", "REPOSITORY ALREADY EXISTS: " + repoPath);
			return propertyList;
		}
		
		File repoFolder = new File(repoPath);
		
		repoFolder.delete();
		repoFolder.mkdirs();
		
		Repository repo = null;
		try {
			repo = FileRepositoryBuilder.create(new File(repoFolder, ".git"));
			repo.create();
		} catch (IOException e) {
			logger.error("RECATALOG: " + e.getMessage()) ;
		}
		
		if (repo != null) {
			try {
				repoPath = repo.getDirectory().getCanonicalPath();
				propertyList.addProperty("OBJECT", repoPath);
			}
			catch (IOException e) {
				logger.error("Error: " + e.getMessage()) ;
			}
		}
			
		return propertyList;
	}

	
	public Repository open(String repoName) {
		File repoFolder = new File(repoPathBase 
									+ System.getProperty("file.separator") 
									+ repoName
									+ System.getProperty("file.separator") 
									+ ".git");
		
		Repository repo = null;
		
		try {
			Git git = Git.open(repoFolder);
			
			if (git != null) {
				repo =  git.getRepository();
			}
		} catch (IOException e) {
			logger.error("Error: " + e.getMessage()) ;
		}
		return repo;
	}	
	
	public String addFolderToRepository(String pathFolderToCopy, Repository gitRepo) {
		File folderToCopy = new File(pathFolderToCopy);
		if(!folderToCopy.isFile()) return null;

		File gitFile = new File(gitRepo.getDirectory().getParent(), folderToCopy.getName());
		try {
			FileUtils.copyFile(folderToCopy, gitFile);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return gitFile.getName();
	}
}