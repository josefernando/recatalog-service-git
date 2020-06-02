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

import br.com.recatalog.service.git.exception.GitAlreadyExistsException;
import br.com.recatalog.service.git.exception.GitRequestException;

@Service
public class GitRepositoryService {
	
	@Value("${repo.path.root}")
	private String repoPathRoot;
	
	private Logger logger = LoggerFactory.getLogger(GitRepositoryService.class);
	
	public String init(String repoName) { 
		
		String repoPath = repoPathRoot + System.getProperty("file.separator") + repoName;
		
		if(open(repoName) != null) {
			throw new GitAlreadyExistsException(String.format("Repository '%s' already exists!!",repoName));
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
			throw new GitRequestException(String.format(e.getMessage(),repoName));
		}
		
		if (repo != null) {
			try {
				repoPath = repo.getDirectory().getCanonicalPath();
			}
			catch (IOException e) {
				logger.error("Error: " + e.getMessage()) ;
				throw new GitRequestException(String.format(e.getMessage(),repoName));
			}
		}
		return repoPath;
	}
	
	public Repository open(String repoName) {
		File repoFolder = new File(repoPathRoot 
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
			throw new GitRequestException(String.format(e.getMessage(),repoName));
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
			logger.error("Error: " + e.getMessage()) ;
			throw new GitRequestException(String.format(e.getMessage(),pathFolderToCopy));
		}
		return gitFile.getName();
	}
}