package io.github.vacxe.yetanotherhometask.view.list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import io.github.vacxe.omdbapi.dto.SearchItem
import io.github.vacxe.yetanotherhometask.ui.theme.YetAnotherHomeTaskTheme

object Views {
    @Composable
    fun Content(
        content: List<SearchItem>?,
        onItemClick: (searchItem: SearchItem) -> Unit
    ) {
        YetAnotherHomeTaskTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                content?.forEach {
                    SearchItemView(it, onItemClick = onItemClick)
                }
            }
        }
    }

    @Composable
    fun SearchInput(search: String, onSearchChanged: (text: String) -> Unit) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            value = search,
            onValueChange = {
                onSearchChanged.invoke(it)
            },
            label = { Text("Movie search") }
        )
    }

    @Composable
    private fun SearchItemView(
        searchItem: SearchItem,
        onItemClick: (searchItem: SearchItem) -> Unit
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, start = 8.dp, end = 8.dp)
            .clickable { onItemClick.invoke(searchItem) }) {
            Image(
                painter = rememberAsyncImagePainter(searchItem.poster),
                contentDescription = null,
                modifier = Modifier.size(96.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = searchItem.title ?: "Unknown",
                    fontSize = 24.sp,
                    style = TextStyle(textAlign = TextAlign.Start)
                )

                Text(
                    text = searchItem.year ?: "Unknown",
                    fontSize = 16.sp,
                    style = TextStyle(textAlign = TextAlign.Center)
                )
            }
        }
    }
}
