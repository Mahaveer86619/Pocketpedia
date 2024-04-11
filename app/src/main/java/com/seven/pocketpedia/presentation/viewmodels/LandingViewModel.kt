package com.seven.pocketpedia.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seven.pocketpedia.domain.repository.DictionaryRepository
import com.seven.pocketpedia.presentation.events.LandingEvent
import com.seven.pocketpedia.presentation.states.LandingState
import com.seven.pocketpedia.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LandingState())
    val state = _state.asStateFlow()

    private val searchJob: Job? = null

    fun onEvent(event: LandingEvent) {
        when (event) {
            LandingEvent.OnSearchClick -> {
                loadWordResult()
            }

            is LandingEvent.OnSearchWordChange -> {
                _state.update {
                    it.copy(
                        searchWord = event.newWord.lowercase()
                    )
                }
            }
        }
    }

    private fun loadWordResult() {
        viewModelScope.launch {
            dictionaryRepository.getWordResult(
                state.value.searchWord
            ).collect { result ->
                when (result) {
                    is Result.Error -> Unit
                    is Result.Loading -> {
                        _state.update { it.copy(
                            isLoading = result.isLoading
                        ) }
                    }
                    is Result.Success -> {
                        result.data?.let { wordItem ->
                            _state.update { it.copy(
                                wordItem = wordItem
                            ) }
                        }
                    }
                }
            }
        }
    }

}