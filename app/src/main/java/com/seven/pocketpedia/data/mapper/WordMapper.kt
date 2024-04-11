package com.seven.pocketpedia.data.mapper

import com.seven.pocketpedia.data.remote.dto.DefinitionDto
import com.seven.pocketpedia.data.remote.dto.MeaningDto
import com.seven.pocketpedia.data.remote.dto.WordItemDto
import com.seven.pocketpedia.domain.model.Definition
import com.seven.pocketpedia.domain.model.Meaning
import com.seven.pocketpedia.domain.model.WordItem

fun WordItemDto.toWordItem() = (meanings?.map {
    it.toMeaning()
} ?: emptyList()).let {
    WordItem (
        word = word ?: "",
        meanings = it,
        phonetic = phonetic ?: "",
    )
}

fun MeaningDto.toMeaning() = definitions?.get(0)?.let { definitionDtoToDefinition(it) }?.let {
    Meaning(
        definition = it,
        partOfSpeech = partOfSpeech ?: "",
    )
}

fun definitionDtoToDefinition(definitionDto: DefinitionDto) = Definition(
    definition = definitionDto.definition ?: "",
    example = definitionDto.example ?: "",
)