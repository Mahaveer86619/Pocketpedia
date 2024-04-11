package com.seven.pocketpedia.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.seven.pocketpedia.domain.model.WordItem

@Composable
fun WordResultDictionary(
    wordItem: WordItem
) {

    LazyColumn(contentPadding = PaddingValues(vertical = 32.dp)) {

        items(wordItem.meanings.size) { index ->
            wordItem.meanings[index]?.let {
                MeaningBox(
                    meaning = it,
                    index = index,
                )
            }
        }

    }
    
}