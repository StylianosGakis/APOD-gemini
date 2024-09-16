package com.stylianosgakis.mars.apod.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.dropUnlessResumed
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stylianosgakis.mars.apod.ApodItem
import com.stylianosgakis.mars.sharedElement
import com.stylianosgakis.mars.theme.MarsTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun ApodDetailsDestination(
    apodDetailsViewModel: ApodDetailsViewModel,
    apodTitle: String,
    apodUrl: String?,
    onNavigateToPhoto: (url: String) -> Unit,
) {
    val uiState: ApodDetailsUiState by apodDetailsViewModel.uiState.collectAsStateWithLifecycle()
    ApodDetailsScreen(
        uiState = uiState,
        apodTitle = apodTitle,
        apodUrl = apodUrl,
        onNavigateToPhoto = onNavigateToPhoto,
        onGenerateGeminiInformation = apodDetailsViewModel::generateGeminiInformation
    )
}

@Composable
private fun ApodDetailsScreen(
    uiState: ApodDetailsUiState,
    apodTitle: String,
    apodUrl: String?,
    onNavigateToPhoto: (url: String) -> Unit,
    onGenerateGeminiInformation: () -> Unit,
) {
    Box(
        Modifier.fillMaxSize(),
        propagateMinConstraints = true,
    ) {
        when (uiState) {
            ApodDetailsUiState.NotFound -> {
                Text(
                    text = "Apod not found!",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.wrapContentSize(Alignment.Center)
                )
            }

            else -> {
                ApodDetailsScreen(
                    (uiState as? ApodDetailsUiState.Data)?.apodItem,
                    (uiState as? ApodDetailsUiState.Data)?.geminiResponseState,
                    apodTitle,
                    apodUrl,
                    onNavigateToPhoto,
                    onGenerateGeminiInformation,
                )
            }
        }
    }
}

@Composable
private fun ApodDetailsScreen(
    apodItem: ApodItem?,
    geminiResponseState: GeminiResponseState?,
    title: String,
    apodUrl: String?,
    onNavigateToPhoto: (url: String) -> Unit,
    onGenerateGeminiInformation: () -> Unit,
) {
    Box {
        Column(
            Modifier
                .matchParentSize()
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(apodUrl)
                    .placeholderMemoryCacheKey(apodUrl)
                    .build(),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .widthIn(max = 500.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .heightIn(min = 250.dp)
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top))
                    .sharedElement(title)
                    .then(
                        if (apodUrl != null) {
                            Modifier.sharedElement(key = apodUrl)
                        } else {
                            Modifier
                        }
                    )
                    .background(MaterialTheme.colorScheme.surfaceDim)
                    .then(
                        if (apodUrl != null) {
                            Modifier.clickable(
                                onClick = dropUnlessResumed { onNavigateToPhoto(apodUrl) }
                            )
                        } else {
                            Modifier
                        }
                    )
            )
            Spacer(Modifier.height(8.dp))
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                if (apodItem != null) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = apodItem.explanation,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = apodItem.date.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                if (geminiResponseState is GeminiResponseState.Data) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Gemini Information",
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = geminiResponseState.response,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
            }
        }
        val density = LocalDensity.current
        if (geminiResponseState !is GeminiResponseState.Data) {
            FloatingActionButton(
                onGenerateGeminiInformation,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Bottom + WindowInsetsSides.End
                        )
                    )
            ) {
                when (geminiResponseState) {
                    GeminiResponseState.Error -> {
                        Icon(Icons.Filled.Warning, contentDescription = "Error")
                    }

                    GeminiResponseState.Loading -> {
                        CircularProgressIndicator()
                    }

                    GeminiResponseState.Idle -> {
                        Text(text = "✨", fontSize = with(density) { 24.dp.toSp() })
                    }

                    else -> {}
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewApodDetailsScreen() {
    MarsTheme {
        ApodDetailsScreen(
            ApodDetailsUiState.Data(
                ApodItem(
                    title = "title",
                    copyright = "copyright",
                    date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
                    explanation = "explanation",
                    url = "url",
                ),
                GeminiResponseState.Idle,
            ),
            "Title",
            null,
            {},
            {},
        )
    }
}