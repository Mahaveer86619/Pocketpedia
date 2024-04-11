package com.seven.pocketpedia.data.mapper

import com.seven.pocketpedia.data.remote.dto.DefinitionDto
import com.seven.pocketpedia.data.remote.dto.MeaningDto
import com.seven.pocketpedia.data.remote.dto.WordItemDto
import com.seven.pocketpedia.domain.model.Definition
import com.seven.pocketpedia.domain.model.Meaning
import com.seven.pocketpedia.domain.model.WordItem

fun WordItemDto.toWordItem() = WordItem (
    word = word ?: "",
    meanings = meanings?.map {
        it.toMeaning()
    } ?: emptyList(),
    phonetic = phonetic ?: "",
)

fun MeaningDto.toMeaning() = Meaning(
    definition = definitionDtoToDefinition(definitions?.get(0)),
    partOfSpeech = partOfSpeech ?: "",
)

fun definitionDtoToDefinition(definitionDto: DefinitionDto) = Definition(
    definition = definitionDto.definition ?: "",
    example = definitionDto.example ?: "",
)