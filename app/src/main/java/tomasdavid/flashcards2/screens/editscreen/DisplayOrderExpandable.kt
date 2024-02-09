package tomasdavid.flashcards2.screens.editscreen

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text

fun LazyListScope.displayOrderExpandable(expanded: Boolean, onToggle: () -> Unit) {
    item {
        ExpandableListHeader(
            title = "Display Order",
            onToggle = onToggle
        )
    }
    if (expanded) {
        items(100) {
            Text("$it")
        }
    }
}