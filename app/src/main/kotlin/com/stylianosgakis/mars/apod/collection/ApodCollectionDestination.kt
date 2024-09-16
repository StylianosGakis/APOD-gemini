package com.stylianosgakis.mars.apod.collection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.stylianosgakis.mars.apod.ApodItem
import com.stylianosgakis.mars.theme.MarsTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun ApodCollectionDestination(
    apodCollectionViewModel: ApodCollectionViewModel,
    onNavigateToApodDetails: (title: String, url: String?) -> Unit,
) {
    val uiState: ApodCollectionUiState by apodCollectionViewModel.uiState.collectAsStateWithLifecycle()
    ApodCollectionScreen(uiState, {}, onNavigateToApodDetails)
//    ApodCollectionScreen(uiState, apodCollectionViewModel::refreshPhotos, onNavigateToApodDetails)
}

@Composable
private fun ApodCollectionScreen(
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
                Text("Something went wrong")
//                Column(
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ) {
//                    Text("Something went wrong")
//                    Button(refresh) {
//                        Text("Try again")
//                    }
//                }
            }

            ApodCollectionUiState.Loading -> {
//                CircularProgressIndicator(
//                    Modifier
//                        .wrapContentSize(Alignment.Center)
//                        .matchParentSize(),
//                )
            }

            is ApodCollectionUiState.Data -> {
//                ApodCollectionScreen(
//                    uiState = uiState,
//                    refresh = refresh,
//                    onNavigateToApodDetails = onNavigateToApodDetails
//                )
            }
        }
    }
}

@Composable
private fun ApodCollectionScreen(
    uiState: ApodCollectionUiState.Data,
    refresh: () -> Unit,
    onNavigateToApodDetails: (title: String, url: String?) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        verticalItemSpacing = 8.dp,
//        contentPadding = PaddingValues(horizontal = 16.dp)
////                + WindowInsets.safeDrawing.asPaddingValues(),
    ) {
////        item(span = StaggeredGridItemSpan.FullLine) {
////            Text(
////                text = "Apod list",
////                style = MaterialTheme.typography.displaySmall,
////                modifier = Modifier.padding(bottom = 8.dp),
////            )
////        }
//        items(
//            items = uiState.apodItems,
//            key = { it.title },
//        ) { apodItem ->
//            ApodListItem(
//                apodItem = apodItem,
//                onClick = {},
////                onClick = dropUnlessResumed {
////                    onNavigateToApodDetails(apodItem.title, apodItem.url)
////                },
//            )
//        }
////        item(span = StaggeredGridItemSpan.FullLine) {
////            Button(
////                onClick = refresh,
////                modifier = Modifier.wrapContentWidth(),
////            ) {
////                Text(
////                    text = "Get a new set of items",
////                )
////            }
////        }
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
        shape = RectangleShape,
//        shape = shape,
//        border = BorderStroke(Dp.Hairline, MaterialTheme.colorScheme.onSurface),
        modifier = modifier,
    ) {
        Column(
            modifier = if (onClick != null) {
                Modifier.clickable(onClick = onClick)
            } else {
                Modifier
            },
        ) {
            AsyncImage(
                model = apodItem.url,
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(apodItem.url)
//                    .memoryCacheKey(apodItem.url)
//                    .build(),
                contentDescription = apodItem.explanation,
                contentScale = ContentScale.Crop,
                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(140.dp)
////                    .sharedElement(key = apodItem.title)
//                    .clip(shape)
                // While loading, show background
//                    .background(MaterialTheme.colorScheme.surfaceDim)
            )
            Column(/*Modifier.padding(8.dp)*/) {
                Text(
                    text = apodItem.title,
//                    style = MaterialTheme.typography.bodyLarge,
//                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = apodItem.explanation,
//                    style = MaterialTheme.typography.bodyMedium,
//                    fontWeight = FontWeight.Light,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewApodCollectionScreen() {
    MarsTheme {
        ApodCollectionScreen(
            ApodCollectionUiState.Data(
                List(5) {
                    ApodItem(
                        title = "title#$it",
                        copyright = "copyright",
                        date = Clock.System.now()
                            .toLocalDateTime(TimeZone.currentSystemDefault()).date,
                        explanation = "explanation",
                        url = null,
                    )
                },
            ),
            {},
            { _, _ -> }
        )
    }
}
