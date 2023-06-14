package com.example.fetchhiring.domain.repository

import com.example.fetchhiring.data.model.HiringResponse

/**
//   FetchHiring
//   Created by Subham Dhakal on 6/13/23.
 */
interface HiringRepository {
    suspend fun getHiring(): HiringResponse
}