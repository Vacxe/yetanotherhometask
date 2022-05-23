package io.github.vacxe.yetanotherhometask.view.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.vacxe.omdbapi.api.OmdbApi
import io.github.vacxe.yetanotherhometask.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val omdbApi: OmdbApi) : ViewModel() {
    val state
        get() = _state

    private val _state = MutableLiveData<MovieDetailsState>(MovieDetailsState.Loading)

    fun getMovieDetail(movieId: String) {
        _state.value = MovieDetailsState.Loading
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = omdbApi.getMovieDetail(movieId)
                _state.value = MovieDetailsState.Content(result)
            } catch (e: Exception) {
                if (BuildConfig.DEBUG) {
                    _state.value = MovieDetailsState.Error(e.message.toString())
                } else {
                    _state.value = MovieDetailsState.Error("Network error")
                }
            }
        }
    }
}
