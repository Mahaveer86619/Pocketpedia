package com.seven.pocketpedia.presentation.states

import com.seven.pocketpedia.domain.model.WordItem

data class LandingState(
    val isLoading: Boolean = false,
    val searchWord: String = "",
    val wordItem: WordItem? = null,
)
