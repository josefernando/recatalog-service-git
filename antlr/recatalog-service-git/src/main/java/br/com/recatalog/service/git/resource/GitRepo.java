package br.com.recatalog.service.git.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.recatalog.service.git.service.GitRepoService;
import br.com.recatalog.util.PropertyList;

@RestController
@RequestMapping("api/git/repos")
public class GitRepo {
	
	@Autowired
	GitRepoService gitRepoService;
	
	@GetMapping("")
	public String test() {
		return "test git!";
	}
	
	@PostMapping("/{repoName}")
	public String init(@PathVariable(name = "repoName") String repoName) {
		PropertyList propertyList = gitRepoService.init(repoName);
		
		if(propertyList.hasProperty("OBJECT")) {
			return (String)propertyList.getProperty("OBJECT");
		}	
		
		if(propertyList.hasProperty("ERROR")) {
			return (String)propertyList.getProperty("ERROR");
		}
		
		return (String)propertyList.mustProperty("EXCEPTION");
	}
}