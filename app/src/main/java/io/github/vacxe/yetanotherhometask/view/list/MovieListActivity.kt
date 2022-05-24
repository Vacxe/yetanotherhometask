package io.github.vacxe.yetanotherhometask.view.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import io.github.vacxe.yetanotherhometask.ui.theme.YetAnotherHomeTaskTheme
import io.github.vacxe.yetanotherhometask.view.CommonView
import io.github.vacxe.yetanotherhometask.view.details.MovieDetailsActivity

@AndroidEntryPoint
class MovieListActivity : ComponentActivity() {
    private val viewModelMovie: MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YetAnotherHomeTaskTheme {
                val state = viewModelMovie.state.observeAsState()
                var search by rememberSaveable { mutableStateOf("") }

                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Views.SearchInput(search, onSearchChanged = {
                        search = it
                        viewModelMovie.updateSearchWithDebounce(it)
                    })
                    when (val activeState = state.value) {
                        is MovieListState.Error -> CommonView.ErrorView(errorMessage = activeState.message)
                        MovieListState.Loading -> CommonView.LoadingView()
                        is MovieListState.Content -> Views.Content(activeState.content,
                            onItemClick = { navigateToDetails(it.imdbId) })
                        null -> throw Exception("Undesirable state")
                    }
                }
            }
        }
    }

    private fun navigateToDetails(imdbID: String?) {
        Intent(this, MovieDetailsActivity::class.java)
            .apply {
                putExtra(MovieDetailsActivity.ImdbID, imdbID)
            }
            .run(::startActivity)
    }
}
