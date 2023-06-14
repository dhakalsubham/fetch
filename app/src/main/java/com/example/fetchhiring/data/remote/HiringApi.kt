package com.example.fetchhiring.data.remote

import com.example.fetchhiring.data.model.HiringResponse
import retrofit2.http.GET

/**
//   FetchHiring
//   Created by Subham Dhakal on 6/13/23.
 */
interface HiringApi {
    @GET("hiring.json")
    suspend fun getHiring():HiringResponse
}