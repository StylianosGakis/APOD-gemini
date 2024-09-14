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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.dropUnlessResumed
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stylianosgakis.mars.LocalAnimatedContentScope
import com.stylianosgakis.mars.LocalSharedTransitionScope
import com.stylianosgakis.mars.apod.ApodItem

@Composable
fun ApodDetailsDestination(
    apodDetailsViewModel: ApodDetailsViewModel,
    apodTitle: String,
    apodUrl: String?,
    onNavigateToPhoto: (url: String) -> Unit,
) {
    val uiState: ApodDetailsUiState by apodDetailsViewModel.uiState.collectAsStateWithLifecycle()
    ApodDetailsScreen(uiState, apodTitle, apodUrl, onNavigateToPhoto)
}

@Composable
private fun ApodDetailsScreen(
    uiState: ApodDetailsUiState,
    apodTitle: String,
    apodUrl: String?,
    onNavigateToPhoto: (url: String) -> Unit,
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
                    apodTitle,
                    apodUrl,
                    onNavigateToPhoto
                )
            }
        }
    }
}

@Composable
private fun ApodDetailsScreen(
    apodItem: ApodItem?,
    title: String,
    apodUrl: String?,
    onNavigateToPhoto: (url: String) -> Unit,
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
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
                .then(
                    with(LocalSharedTransitionScope.current) {
                        Modifier.sharedElement(
                            state = rememberSharedContentState(title),
                            animatedVisibilityScope = LocalAnimatedContentScope.current,
                        )
                    }
                )
                .then(
                    if (apodUrl != null) {
                        with(LocalSharedTransitionScope.current) {
                            Modifier.sharedElement(
                                state = rememberSharedContentState(apodUrl),
                                animatedVisibilityScope = LocalAnimatedContentScope.current,
                            )
                        }
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
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        if (apodItem != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = apodItem.explanation,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = apodItem.date.toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.End)
            )
        }
    }
}
