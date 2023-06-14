package com.example.fetchhiring.feature.hiring

import com.example.fetchhiring.data.model.ListItem

/**
//   FetchHiring
//   Created by Subham Dhakal on 6/13/23.
 */
data class HiringStateHolder(
    val isLoading:Boolean = false,
    val data: MutableList<ListItem>? = null,
    val error: String = ""
)
