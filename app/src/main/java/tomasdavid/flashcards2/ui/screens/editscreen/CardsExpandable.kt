package tomasdavid.flashcards2.ui.screens.editscreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import tomasdavid.flashcards2.ui.screens.Card
import tomasdavid.flashcards2.ui.screens.Set
import tomasdavid.flashcards2.viewmodels.SetViewModel

fun LazyListScope.cardsExpandable(
    expanded: Boolean,
    expandToggle: () -> Unit,
    expandedItemIndex: Int?,
    setExpandedItemIndex: (Int?) -> Unit,
    setExpandedItemPosition: (Position?) -> Unit,
    set: Set,
    addCard: (Card) -> Unit,
    updateCard: (text1: String?, text2: String?, cardImg: String?) -> Unit,
    setExpandedCard: (Card) -> Unit
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
        Divider()
    }
    if (expanded) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            setExpandedItemIndex(set.cards.size)
                            addCard(Card())
                        },
                        onClickLabel = "Add card"
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Add card")
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add icon"
                )
            }
            Divider()
        }
        items(set.cards.size) {i ->
            // reversed order
            val index = set.cards.size - 1 - i

            CardItem(
                cardIndex = index,
                expandedItemIndex = expandedItemIndex,
                setExpandedItemIndex = setExpandedItemIndex,
                setExpandedItemPosition = setExpandedItemPosition,
                card = set.cards[index],
                updateCard = updateCard,
                setExpandedCard = setExpandedCard
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}