package com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.git

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.ActionOnPullRequestInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract.Git
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.git.GitRepositoryExt
import javax.inject.Inject

class CheckoutBranchInteractor
    @Inject
    constructor(
            private val branchOperator: Git.BranchOperator,
            private val gitRepositoryFinder: RepositoriesDomainContract.Git.RepositoryFinder)
    : ActionOnPullRequestInteractor {
    override fun run(request: PullRequestEntity) {
        val repository = gitRepositoryFinder.findRootRepo()!!
        val branchName = request.head?.ref!!
        if (repository is GitRepositoryExt && repository.currentBranch?.name != branchName) {
            branchOperator.checkOut(branchName, repository)
        }
    }
}
