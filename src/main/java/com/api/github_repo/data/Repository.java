package com.api.github_repo.data;

import java.util.ArrayList;

public class Repository {
    private String repoName;
    private String owner;
    private ArrayList<Branch> branches;

    public Repository(String repoName, String owner, ArrayList<Branch> branches) {
        this.repoName = repoName;
        this.owner = owner;
        this.branches = branches;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<Branch> getBranch() {
        return branches;
    }

    public void setBranch(ArrayList<Branch> branch) {
        this.branches = branch;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "repoName='" + repoName + '\'' +
                ", owner='" + owner + '\'' +
                ", branches=" + branches +
                '}';
    }
}
