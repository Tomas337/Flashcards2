package tomasdavid.flashcards2.screens.editscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun CardItem() {
    Row(
       modifier = Modifier,
    ) {
        AsyncImage(
            model = "file:///android_asset/no-image.1024x1024.png",
            contentDescription = "Card image",
        )
    }
}