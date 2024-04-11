package com.seven.pocketpedia.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seven.pocketpedia.R
import com.seven.pocketpedia.presentation.components.LandingContent
import com.seven.pocketpedia.presentation.events.LandingEvent
import com.seven.pocketpedia.presentation.viewmodels.LandingViewModel

@Composable
fun LandingScreen(
    viewmodel: LandingViewModel = hiltViewModel()
) {

    val state by viewmodel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = state.searchWord,
                onValueChange = { inputString ->
                    viewmodel.onEvent(
                        LandingEvent.OnSearchWordChange(inputString)
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                viewmodel.onEvent(
                                    LandingEvent.OnSearchClick
                                )
                            }
                    )
                },
                label = {
                    Text(
                        text = "Search a word",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .alpha(0.7f)
                    )
                },
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 19.5.sp,
                )
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
        ) {

            LandingContent(state = state)

        }

    }

}