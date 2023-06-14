package com.example.fetchhiring.data.repository

import com.example.fetchhiring.data.model.HiringResponse
import com.example.fetchhiring.data.remote.HiringApi
import com.example.fetchhiring.domain.repository.HiringRepository

/**
//   FetchHiring
//   Created by Subham Dhakal on 6/13/23.
 */
class HiringRepoImpl(private val hiringApi: HiringApi) : HiringRepository {
    override suspend fun getHiring(): HiringResponse {
        return hiringApi.getHiring()
    }
}