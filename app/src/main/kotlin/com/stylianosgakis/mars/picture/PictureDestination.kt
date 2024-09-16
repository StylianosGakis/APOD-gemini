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

@Composable
fun PictureDestination(pictureUrl: String) {
    // https://github.com/mxalbert1996/Zoomable
//    Zoomable {
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
//                .sharedElement(key = pictureUrl)
        )
//    }
}