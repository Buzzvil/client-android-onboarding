package com.buzzvil.onboarding.infrastructure.network

import com.buzzvil.onboarding.domain.model.Ad
import com.buzzvil.onboarding.infrastructure.mapper.Mapper
import io.reactivex.Single
import javax.inject.Inject

class BuzzAdClient @Inject constructor(
    private val buzzAdApi: BuzzAdApi,
    private val mapper: Mapper
) {
    fun get(): Single<Ad> {
        return buzzAdApi.get().map {
            mapper.of(it)
        }
    }
}
