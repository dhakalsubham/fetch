package com.example.fetchhiring.domain.repository.use_cases

import com.example.fetchhiring.common.UiEvent
import com.example.fetchhiring.domain.repository.HiringRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
//   FetchHiring
//   Created by Subham Dhakal on 6/13/23.
 */
class GetHiringUseCases (private val hiringRepository: HiringRepository){
    operator fun invoke() = flow {
        emit(UiEvent.Loading())
        emit(UiEvent.Success(hiringRepository.getHiring()))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}