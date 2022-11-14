package com.buzzvil.onboarding.domain.repository

import com.buzzvil.onboarding.domain.model.Ad
import io.reactivex.Single

interface AdRepository {
    fun get(): Single<Ad>
}
