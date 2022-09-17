package com.api.github_repo.web;

import com.api.github_repo.data.Branch;
import com.api.github_repo.data.Repository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.io.IOException;
import java.util.*;

@RestController
public class GithubController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello world";
    }


    @GetMapping("/branches")
    public ArrayList<Branch> getBranches(String username, String repoName) throws IOException {

        String urlBranch = "https://api.github.com/repos/" + username + "/" + repoName + "/branches";
        RestTemplate restTemplateBranch = new RestTemplate();
        Object[] branches = restTemplateBranch.getForObject(urlBranch, Object[].class);
        ObjectMapper oMapperBranch = new ObjectMapper();
        Map<String, Object> mapBranch = null;
        ArrayList<Branch> branchesArray = new ArrayList<>();
        assert branches != null;
        for (Object branch : branches) {
            mapBranch = oMapperBranch.convertValue(branch, Map.class);
            HashMap mapping = new ObjectMapper().convertValue(mapBranch.get("commit"), HashMap.class);
            Branch newBranch = new Branch((String) mapBranch.get("name"), (String) mapping.get("sha"));
            branchesArray.add(newBranch);

        }
        return branchesArray;
    }


    @GetMapping("/repos/{username}")
    public ArrayList<Repository> getUser(@PathVariable String username) throws IOException {
        String url = "https://api.github.com/users/" + username + "/repos";
        ArrayList<Repository> repositoriesArray = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        Object[] repositories = restTemplate.getForObject(url, Object[].class);
        ObjectMapper oMapper = new ObjectMapper();


        assert repositories != null;
        for (Object repo : repositories) {
            Map map = oMapper.convertValue(repo, Map.class);


            if (!(Boolean) map.get("fork")) {
                ArrayList<Branch> branches = getBranches(username, (String) map.get("name"));
                Repository newRepo = new Repository((String) map.get("name"), username, branches);
                repositoriesArray.add(newRepo);
            }

        }


        return repositoriesArray;

    }


}


