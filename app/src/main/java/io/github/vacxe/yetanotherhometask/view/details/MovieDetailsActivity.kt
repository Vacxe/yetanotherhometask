package io.github.vacxe.yetanotherhometask.view.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import dagger.hilt.android.AndroidEntryPoint
import io.github.vacxe.yetanotherhometask.ui.theme.YetAnotherHomeTaskTheme
import io.github.vacxe.yetanotherhometask.view.CommonView

@AndroidEntryPoint
class MovieDetailsActivity : ComponentActivity() {
    companion object {
        const val ImdbID = "ImdbID"
    }

    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieDetail(intent.extras?.getString(ImdbID) ?: "")
        setContent {
            val state = viewModel.state.observeAsState()

            YetAnotherHomeTaskTheme {
                Surface(color = MaterialTheme.colors.background) {
                    when (val activeState = state.value) {
                        is MovieDetailsState.Error -> CommonView.ErrorView(errorMessage = activeState.message)
                        MovieDetailsState.Loading -> CommonView.LoadingView()
                        is MovieDetailsState.Content -> Views.Content(activeState.content)
                        null -> throw Exception("Undesirable state")
                    }
                }
            }
        }
    }
}
