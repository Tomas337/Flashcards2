package tomasdavid.flashcards2.ui.screens.editscreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import tomasdavid.flashcards2.ui.screens.Card

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(
    cardId: Int,
    expandedItemId: Int?,
    setExpandedItemId: (Int?) -> Unit,
    setExpandedItemPosition: (Position?) -> Unit,
    inEditMode: Boolean = (cardId == expandedItemId),
    updateCard: (Card) -> Unit
) {
    val cardItemHeight = if (!inEditMode) {
        70.dp
    } else {
        150.dp
    }
    val textSize = if (!inEditMode) {
        20.sp
    } else {
        25.sp
    }
    val padding = if (!inEditMode) {
        10.dp
    } else {
        15.dp
    }

    var position by remember { mutableStateOf<Position?>(null) }
    
    var text1 by remember { mutableStateOf("Text1") }
    var text2 by remember { mutableStateOf("Text2") }

    val focusManager = LocalFocusManager.current

    // TODO delete on swipe
    Box(
        modifier = Modifier
            .onGloballyPositioned { coordinates ->
                position = Position(
                    height = coordinates.size.height,
                    width = coordinates.size.width,
                    x = coordinates
                        .positionInRoot()
                        .round().x,
                    y = coordinates
                        .positionInRoot()
                        .round().y
                )
                if (inEditMode) {  // update position state on box expansion
                    setExpandedItemPosition(position)
                }
            }
            .clickable(
                enabled = (expandedItemId == null),
            ) {
                if (expandedItemId == null) {
                    setExpandedItemId(cardId)
                    setExpandedItemPosition(position)
                }
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
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
                    .padding(horizontal = padding)
            ) {
                if (!inEditMode) {
                    Text(
                        text = text1,
                        fontSize = textSize,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Divider()
                    Text(
                        text = text2,
                        fontSize = textSize,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                } else {
                    // TODO switch to BasicTextField to style padding
                    OutlinedTextField(
                        value = text1,
                        onValueChange = { text1 = it },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = textSize
                        ),
                        modifier = Modifier
                            .clickable(enabled = true) {}
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        singleLine = true
                    )
                    Divider()
                    OutlinedTextField(
                        value = text2,
                        onValueChange = { text2 = it },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = textSize
                        ),
                        modifier = Modifier
                            .clickable(enabled = true) {}
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        singleLine = true
                    )
                }
            }
        }
    }
}