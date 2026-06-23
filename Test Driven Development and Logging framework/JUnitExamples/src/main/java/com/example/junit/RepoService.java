package com.example.junit;

public class RepoService {
    private final Repository repo;

    public RepoService(Repository repo) { this.repo = repo; }

    public String processData() {
        return "Processed " + repo.getData();
    }
}
