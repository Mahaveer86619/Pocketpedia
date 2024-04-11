package com.seven.pocketpedia.domain.repository

import com.seven.pocketpedia.domain.model.WordItem
import com.seven.pocketpedia.util.Result
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {

    suspend fun getWordResult(
        word: String
    ): Flow<Result<WordItem>>

}