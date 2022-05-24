package io.github.vacxe.yetanotherhometask.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.vacxe.data.movie.MovieSearchParameters
import io.github.vacxe.domain.interactors.Interactor
import io.github.vacxe.domain.mappers.imdb.MovieSearch
import io.github.vacxe.yetanotherhometask.BuildConfig
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val searchInteractor: Interactor<MovieSearchParameters, MovieSearch>) : ViewModel() {
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

    private fun updateSearch(search: String) {
        _state.value = MovieListState.Loading
        CoroutineScope(Dispatchers.Main).launch {
            try {
                when(val result = searchInteractor.execute(MovieSearchParameters(search))){
                    is MovieSearch.Error -> _state.value = MovieListState.Error(result.message)
                    is MovieSearch.Success -> _state.value = MovieListState.Content(result.movies)
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
