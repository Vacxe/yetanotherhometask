package io.github.vacxe.yetanotherhometask.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.vacxe.omdbapi.api.OmdbApi
import io.github.vacxe.yetanotherhometask.BuildConfig
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val omdbApi: OmdbApi) : ViewModel() {
    private companion object {
        const val searchDebounce = 1000L
    }

    val state
        get() = _state

    private val _state = MutableLiveData<MovieListState>(MovieListState.Content(emptyList()))

    private var searchJob: Job? = null

    fun updateSearchWithDebounce(search: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(searchDebounce)
            updateSearch(search)
        }
    }

    fun updateSearch(search: String) {
        _state.value = MovieListState.Loading
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = omdbApi.getMovieList(
                    search = search,
                    type = "movie",
                    page = 1
                )
                result.error?.run {
                    _state.value = MovieListState.Error(this)
                } ?: run {
                    _state.value = MovieListState.Content(result.search)
                }
            } catch (e: Exception) {
                if (BuildConfig.DEBUG) {
                    _state.value = MovieListState.Error(e.message.toString())
                } else {
                    _state.value = MovieListState.Error("Network error")
                }
            }
        }
    }
}
