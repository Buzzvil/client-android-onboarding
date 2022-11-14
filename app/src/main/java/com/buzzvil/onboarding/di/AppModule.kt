package com.buzzvil.onboarding.di

import com.buzzvil.onboarding.domain.repository.AdRepository
import com.buzzvil.onboarding.infrastructure.network.BuzzAdApi
import com.buzzvil.onboarding.infrastructure.repository.AdRepositoryImpl
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class AppModule {
    @Binds
    abstract fun bindsAdRepository(
        adRepositoryImpl: AdRepositoryImpl
    ): AdRepository

    companion object {
        @Provides
        private fun provideBuzzAdApi(
            retrofit: Retrofit
        ): BuzzAdApi {
            return retrofit.create(BuzzAdApi::class.java)
        }

        @Provides
        private fun provideGson(): Gson {
            return Gson().newBuilder()
                .setLenient()
                .create()
        }

        @Provides
        private fun provideRetrofit(
            gson: Gson
        ): Retrofit {
            val gsonConverterFactory = GsonConverterFactory.create(gson)
            return Retrofit.Builder()
                .baseUrl("https://s3-ap-northeast-1.amazonaws.com/buzzvi.test/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}