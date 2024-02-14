package tomasdavid.flashcards2.ui.screens.editscreen

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Divider
import androidx.compose.material3.Text

fun LazyListScope.displayOrderExpandable(expanded: Boolean, onToggle: () -> Unit) {
    item {
        ExpandableListHeader(
            title = "Display Order",
            onToggle = onToggle
        )
        Divider()
    }
    if (expanded) {
        items(100) {
            Text("$it")
        }
    }
}