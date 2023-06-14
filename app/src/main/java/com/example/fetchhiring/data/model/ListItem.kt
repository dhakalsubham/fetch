package com.example.fetchhiring.data.model

/**
//   FetchHiring
//   Created by Subham Dhakal on 6/13/23.
 */
sealed class ListItem {
    data class RowItem(val itemModel: HiringResponseItem) : ListItem()
    data class HeaderItem(val listId: Int) : ListItem()
}
