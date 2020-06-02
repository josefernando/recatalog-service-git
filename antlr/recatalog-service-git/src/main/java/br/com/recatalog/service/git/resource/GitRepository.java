package br.com.recatalog.service.git.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.recatalog.service.git.service.GitRepositoryService;

@RestController
@RequestMapping("api/git")
public class GitRepository {
	
	@Autowired
	GitRepositoryService gitRepoService;
	
	@GetMapping("")
	public String test() {
		return "test git!";
	}
	
	@PostMapping("repositories/{repoName}")
	public ResponseEntity<Object> init(@PathVariable(name = "repoName") String repoName) {
		String gitFolder = gitRepoService.init(repoName);
		return new ResponseEntity<>(gitFolder, HttpStatus.CREATED);
	}
	
	@PostMapping("/repositories")
	public ResponseEntity<Object> initx(@RequestBody String repoName) {
		String gitFolder = gitRepoService.init(repoName);
		return new ResponseEntity<>(gitFolder, HttpStatus.CREATED);
	}
}