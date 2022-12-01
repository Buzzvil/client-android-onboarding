package com.buzzvil.onboarding.di

import com.buzzvil.onboarding.domain.repository.AdRepository
import com.buzzvil.onboarding.infrastructure.mapper.Mapper
import com.buzzvil.onboarding.infrastructure.network.BuzzAdApi
import com.buzzvil.onboarding.infrastructure.network.BuzzAdClient
import com.buzzvil.onboarding.infrastructure.repository.AdRepositoryImpl
import com.buzzvil.onboarding.presentation.AdViewModelFactory
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Container {
    fun provideAdViewModelFactory(): AdViewModelFactory {
        val adRepository = provideAdRepository()
        return AdViewModelFactory(adRepository)
    }

    private fun provideAdRepository(): AdRepository {
        val buzzAdClient = provideBuzzAdClient()
        return AdRepositoryImpl(buzzAdClient)
    }

    private fun provideBuzzAdClient(): BuzzAdClient {
        val buzzAdApi = provideBuzzAdApi()
        val mapper = Mapper()
        return BuzzAdClient(buzzAdApi, mapper)
    }

    private fun provideBuzzAdApi(): BuzzAdApi {
        val retrofit = provideRetrofit()
        return retrofit.create(BuzzAdApi::class.java)
    }

    private fun provideRetrofit(): Retrofit {
        val gson = provideGson()
        val l = HttpLoggingInterceptor()
            l.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(l)
            .build()
        val gsonConverterFactory = GsonConverterFactory.create(gson)
        return Retrofit.Builder()
            .baseUrl("https://s3-ap-northeast-1.amazonaws.com/buzzvi.test/")
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun provideGson(): Gson {
        return Gson().newBuilder()
            .setLenient()
            .create()
    }
}
