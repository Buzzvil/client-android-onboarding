package com.buzzvil.onboarding.infrastructure.network

import com.buzzvil.onboarding.domain.model.Ad
import io.reactivex.Single
import javax.inject.Inject

class BuzzAdClient @Inject constructor(
    private val buzzAdApi: BuzzAdApi
) {
    fun get(): Single<Ad> {
        return buzzAdApi.get()
    }
}
