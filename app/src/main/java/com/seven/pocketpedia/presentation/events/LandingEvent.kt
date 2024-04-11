package com.seven.pocketpedia.presentation.events

sealed class LandingEvent {

    data class OnSearchWordChange(val newWord: String): LandingEvent()

    data object OnSearchClick: LandingEvent()

}