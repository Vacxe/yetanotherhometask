package io.github.vacxe.yetanotherhometask.view.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import io.github.vacxe.omdbapi.dto.MovieDetailResponse
import io.github.vacxe.yetanotherhometask.ui.theme.YetAnotherHomeTaskTheme

object Views {
    @Composable
    fun Content(content: MovieDetailResponse) {
        YetAnotherHomeTaskTheme {
            Surface(color = MaterialTheme.colors.background) {
                Image(
                    painter = rememberAsyncImagePainter(content.poster),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                        .alpha(0.07f),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(Color.Transparent)
                ) {
                    Text(
                        text = content.title ?: "Unknown title",
                        fontSize = 32.sp,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp),
                        style = TextStyle(textAlign = TextAlign.Center)
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(content.poster),
                            contentDescription = null,
                            modifier = Modifier.size(128.dp),
                        )

                        Text(
                            text = content.plot ?: "Plot doesn't exist",
                            fontSize = 16.sp,
                            modifier = Modifier.fillMaxSize(),
                            style = TextStyle(textAlign = TextAlign.Start)
                        )
                    }

                    content.year?.let { InfoRow(key = "Year", value = it) }
                    content.actors?.let { InfoRow(key = "Actors", value = it) }
                    content.language?.let { InfoRow(key = "Language", value = it) }
                    content.runtime?.let { InfoRow(key = "Runtime", value = it) }
                }
            }
        }
    }

    @Composable
    fun InfoRow(key: String, value: String) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(
                    top = 8.dp,
                    bottom = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            Text(
                text = key,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxSize(),
                style = TextStyle(
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "\t· $value",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxSize(),
                style = TextStyle(textAlign = TextAlign.Start)
            )
        }
    }
}