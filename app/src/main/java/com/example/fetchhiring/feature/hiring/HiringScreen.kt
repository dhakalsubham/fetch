package com.example.fetchhiring.feature.hiring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fetchhiring.data.model.ListItem

/**
//   FetchHiring
//   Created by Subham Dhakal on 6/13/23.
 */

@Composable
fun HiringScreen() {
    val viewModel = hiltViewModel<HiringViewModel>()
    val hiringStateHolder = viewModel.hiringState.value
    Scaffold(topBar = {
        Box(
            modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color.Black), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Fetch Hiring",
                color = Color.White
            )
        }

    }) {
        if (hiringStateHolder.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        if (hiringStateHolder.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = hiringStateHolder.error)
            }
        }
        if (hiringStateHolder.data != null) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(hiringStateHolder.data) { item ->
                    when (item) {
                        is ListItem.HeaderItem -> HeaderItem(item = item)
                        is ListItem.RowItem -> RowItem(item = item)
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderItem(item: ListItem.HeaderItem) {
    Box(
        Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(8.dp)
    ) {
        Text(text = "List Id: ${item.listId}")
    }
}

@Composable
fun RowItem(item: ListItem.RowItem) {
    Column(
        modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)


    ) {
        Text(text = "List Id: ${item.itemModel.listId}")
        Text(text = "Name : ${item.itemModel.name}")
        Text(text = "Id: ${item.itemModel.id}")
        Divider()
    }

}
