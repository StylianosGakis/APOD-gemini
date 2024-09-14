package com.stylianosgakis.mars.apod.collection

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.dropUnlessResumed
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stylianosgakis.mars.LocalAnimatedContentScope
import com.stylianosgakis.mars.LocalSharedTransitionScope
import com.stylianosgakis.mars.apod.ApodItem
import com.stylianosgakis.mars.plus

@Composable
fun ApodCollectionDestination(
    apodCollectionViewModel: ApodCollectionViewModel,
    onNavigateToApodDetails: (title: String, url: String?) -> Unit,
) {
    val uiState: ApodCollectionUiState by apodCollectionViewModel.uiState.collectAsStateWithLifecycle()
    ApodListScreen(uiState, apodCollectionViewModel::refreshPhotos, onNavigateToApodDetails)
}

@Composable
fun ApodListScreen(
    uiState: ApodCollectionUiState,
    refresh: () -> Unit,
    onNavigateToApodDetails: (title: String, url: String?) -> Unit,
) {
    Box(
        Modifier.fillMaxSize(),
        propagateMinConstraints = true,
    ) {
        when (uiState) {
            ApodCollectionUiState.Error -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text("Something went wrong")
                    Button(refresh) {
                        Text("Try again")
                    }
                }
            }

            ApodCollectionUiState.Loading -> {
                CircularProgressIndicator(
                    Modifier
                        .wrapContentSize(Alignment.Center)
                        .matchParentSize(),
                )
            }

            is ApodCollectionUiState.Data -> ApodListScreen(
                uiState = uiState,
                refresh = refresh,
                onNavigateToApodDetails = onNavigateToApodDetails
            )
        }
    }
}

@Composable
private fun ApodListScreen(
    uiState: ApodCollectionUiState.Data,
    refresh: () -> Unit,
    onNavigateToApodDetails: (title: String, url: String?) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp) + WindowInsets.safeDrawing.asPaddingValues(),
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            Text(
                text = "Apod list",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }
        items(
            items = uiState.apodItems,
            key = { it.title },
        ) { apodItem ->
            ApodListItem(
                apodItem = apodItem,
                onClick = dropUnlessResumed {
                    onNavigateToApodDetails(apodItem.title, apodItem.url)
                },
            )
        }
        item(span = StaggeredGridItemSpan.FullLine) {
            Button(
                onClick = refresh,
                modifier = Modifier.wrapContentWidth(),
            ) {
                Text(
                    text = "Get a new set of items",
                )
            }
        }
    }
}

@Composable
private fun ApodListItem(
    apodItem: ApodItem,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val shape = MaterialTheme.shapes.medium
    Card(
        shape = shape,
        border = BorderStroke(Dp.Hairline, MaterialTheme.colorScheme.onSurface),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.then(
                if (onClick != null) {
                    Modifier.clickable(onClick = onClick)
                } else {
                    Modifier
                }
            ),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(apodItem.url)
                    .memoryCacheKey(apodItem.url)
                    .build(),
                contentDescription = apodItem.explanation,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .then(
                        with(LocalSharedTransitionScope.current) {
                            Modifier.sharedElement(
                                state = rememberSharedContentState(apodItem.title),
                                animatedVisibilityScope = LocalAnimatedContentScope.current,
                            )
                        }
                    )
                    .clip(shape)
                    .background(MaterialTheme.colorScheme.surfaceDim)
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = apodItem.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = apodItem.explanation,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
