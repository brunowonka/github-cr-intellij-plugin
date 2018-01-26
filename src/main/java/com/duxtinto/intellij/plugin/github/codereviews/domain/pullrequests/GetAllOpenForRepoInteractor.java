package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@ProjectScoped
public class GetAllOpenForRepoInteractor implements Interactor<GithubRepositoryEntity, List<PullRequestEntity>> {
    private final PullRequestDomainContract.Repository repository;

    @Inject
    public GetAllOpenForRepoInteractor(PullRequestDomainContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<PullRequestEntity> run(GithubRepositoryEntity repo) {
        try {
            return repository.getAllOpenBy(repo.getOwnerName(), repo.getName());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}