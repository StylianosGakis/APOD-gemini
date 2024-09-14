package com.stylianosgakis.mars.picture

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mxalbert.zoomable.Zoomable
import com.stylianosgakis.mars.LocalAnimatedContentScope
import com.stylianosgakis.mars.LocalSharedTransitionScope

@Composable
fun PictureDestination(pictureUrl: String) {
    Zoomable {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pictureUrl)
                .placeholderMemoryCacheKey(pictureUrl)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .wrapContentHeight()
                .then(
                    run {
                        val sharedTransitionScope = LocalSharedTransitionScope.current
                        val animatedContentScope = LocalAnimatedContentScope.current
                        if (sharedTransitionScope != null && animatedContentScope != null) {
                            with(sharedTransitionScope) {
                                Modifier.sharedElement(
                                    state = rememberSharedContentState(pictureUrl),
                                    animatedVisibilityScope = animatedContentScope,
                                )
                            }
                        } else {
                            Modifier
                        }
                    }
                )

        )
    }
}