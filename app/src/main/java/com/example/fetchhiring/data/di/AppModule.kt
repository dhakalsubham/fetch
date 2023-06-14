package com.example.fetchhiring.data.di

import com.example.fetchhiring.data.remote.HiringApi
import com.example.fetchhiring.data.repository.HiringRepoImpl
import com.example.fetchhiring.domain.repository.HiringRepository
import com.example.fetchhiring.domain.repository.use_cases.GetHiringUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
//   FetchHiring
//   Created by Subham Dhakal on 6/13/23.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerRetrofit(): HiringApi {
        return Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HiringApi::class.java)
    }

    @Provides
    @Singleton
    fun providerRepository(hiringApi: HiringApi): HiringRepository {
        return HiringRepoImpl(hiringApi)
    }

    @Provides
    @Singleton
    fun providesHiringUseCases(hiringRepository: HiringRepository): GetHiringUseCases {
        return GetHiringUseCases(hiringRepository)
    }
}