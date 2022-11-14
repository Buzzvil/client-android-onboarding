package com.buzzvil.onboarding.infrastructure.repository

import com.buzzvil.onboarding.infrastructure.network.BuzzAdClient
import com.buzzvil.onboarding.domain.model.Ad
import com.buzzvil.onboarding.domain.repository.AdRepository
import io.reactivex.Single
import javax.inject.Inject

class AdRepositoryImpl @Inject constructor(
    private val buzzAdClient: BuzzAdClient
): AdRepository {
    override fun get(): Single<Ad> {
        return buzzAdClient.get()
    }
}
