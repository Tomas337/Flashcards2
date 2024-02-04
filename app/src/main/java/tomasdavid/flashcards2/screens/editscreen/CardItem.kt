package tomasdavid.flashcards2.screens.editscreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CardItem(
    cardId: Int,
    expandedItemId: Int?,
    setExpandedItemId: (Int?) -> Unit
) {
    val inEditMode = cardId == expandedItemId

    val cardItemHeight = 70.dp
    val textSize = 20.sp

    var size by remember { mutableStateOf(IntSize.Zero) }
    var position by remember { mutableStateOf(Offset.Zero) }

    // TODO delete on swipe
    // TODO increase size in edit mode
    // TODO exit edit mode by clicking outside
    Box(
        modifier = Modifier
            .onGloballyPositioned { coordinates ->
                size = coordinates.size
                position = coordinates.positionInRoot()
            }
            // only works when clicking on CardItem, should be able to click anywhere
            .clickable(
                onClick = {
                    if (expandedItemId == null) {
                        setExpandedItemId(cardId)
                    } else if (expandedItemId != cardId) {
                        setExpandedItemId(null)
                    }
                },
            )
        // always sets ExpandedId to null because tap gesture is detected by all items
        // TODO tap gesture for the whole screen

//            .pointerInput(Unit) {
//                detectTapGestures { offset ->
//                    Log.i("id", "$expandedItemId, $cardId")
//                    if (expandedItemId == null) {
//                        setExpandedItemId(cardId)
//                    } else if (expandedItemId != cardId) {
//                        setExpandedItemId(null)
//                    }
//
//                }
//            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            AsyncImage(
                model = "file:///android_asset/no-image.1024x1024.png",
                contentDescription = "Card image",
                modifier = Modifier
                    .size(cardItemHeight)
                    .clickable(
                        enabled = inEditMode,
                        onClick = { /*TODO*/ },
                        onClickLabel = "Change card image"
                    )
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .height(cardItemHeight)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "Text1",
                    fontSize = textSize,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Divider()
                Text(
                    text = "Text2",
                    fontSize = textSize,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        if (inEditMode) {
            Text("Delete")
        }

    }
}