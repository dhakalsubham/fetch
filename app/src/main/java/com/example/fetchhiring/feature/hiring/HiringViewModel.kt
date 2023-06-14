package com.example.fetchhiring.feature.hiring

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchhiring.common.UiEvent
import com.example.fetchhiring.data.model.HiringResponseItem
import com.example.fetchhiring.data.model.ListItem
import com.example.fetchhiring.domain.repository.HiringRepository
import com.example.fetchhiring.domain.repository.use_cases.GetHiringUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
//   FetchHiring
//   Created by Subham Dhakal on 6/13/23.
 */


//Display all the items grouped by "listId"
//Sort the results first by "listId" then by "name" when displaying.
//Filter out any items where "name" is blank or null.

@HiltViewModel
class HiringViewModel @Inject constructor(
    private val getHiringUseCases: GetHiringUseCases,
) : ViewModel() {


    private val _hiringState = mutableStateOf(HiringStateHolder())
    val hiringState: State<HiringStateHolder> get() = _hiringState

    init {
        getHiring()
    }

    private fun getHiring() = viewModelScope.launch {
        getHiringUseCases().onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _hiringState.value = HiringStateHolder(isLoading = true)
                }

                is UiEvent.Error -> {
                    _hiringState.value = HiringStateHolder(error = it.message.toString())
                }

                is UiEvent.Success -> {
                    val data = mutableListOf<HiringResponseItem>()
                    it.data?.forEach { element ->
                        data.add(element)
                    }
                    _hiringState.value = HiringStateHolder(data = itemModelToListItem(data))
                }
            }
        }.launchIn(viewModelScope)

    }
    private fun itemModelToListItem(allItems: MutableList<HiringResponseItem>): MutableList<ListItem> {
        val sortedDistinctGroup = allItems.distinctBy { itemModel ->
            itemModel.listId
        }.sortedBy { itemModel -> itemModel.listId }
        val list = mutableListOf<ListItem>()
        allItems.removeIf { value -> value.name == " " || value.name.isNullOrEmpty() }
        allItems.sortWith(compareBy({ it.listId }, { it.id }))
        sortedDistinctGroup.forEach { listId ->
            list.add(ListItem.HeaderItem(listId.listId))
            allItems.forEach() {
                if (listId.listId == it.listId) {
                    list.add(ListItem.RowItem(it))
                }
            }
        }
        return list
    }
}