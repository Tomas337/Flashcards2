package tomasdavid.flashcards2.screens.editscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp

fun LazyListScope.cardsExpandable(
    expanded: Boolean,
    expandToggle: () -> Unit,
    expandedItemId: Int?,
    setExpandedItemId: (Int?) -> Unit,
    setExpandedItemPosition: (Position?) -> Unit
) {
    item {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Cards")
            IconButton(onClick = { expandToggle() }) {
                Icon(
                    imageVector = if (expanded) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = "Expand list"
                )
            }
        }
    }
    if (expanded) {
        items(10) {index ->
            CardItem(
                cardId = index,
                expandedItemId = expandedItemId,
                setExpandedItemId = setExpandedItemId,
                setExpandedItemPosition = setExpandedItemPosition
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}