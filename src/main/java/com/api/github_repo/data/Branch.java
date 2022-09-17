package com.api.github_repo.data;

public class Branch {
    private String branchName;
    private String sha;

    public Branch(String branchName, String sha) {
        this.branchName = branchName;
        this.sha = sha;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }
}
