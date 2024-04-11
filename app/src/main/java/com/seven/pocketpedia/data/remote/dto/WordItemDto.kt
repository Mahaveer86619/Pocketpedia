package com.seven.pocketpedia.data.remote.dto

data class WordItemDto(
    val meanings: List<MeaningDto>? = null,
    val origin: String? = null,
    val phonetic: String? = null,
    val word: String? = null,
)