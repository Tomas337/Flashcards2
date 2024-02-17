package tomasdavid.flashcards2.ui.screens.mainscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import tomasdavid.flashcards2.navigation.Screen
import tomasdavid.flashcards2.ui.screens.Set

@Composable
fun SetItem(
    set: Set,
    navController: NavController
) {
    val setItemHeight = 70.dp
    val textSize = 20.sp
    val padding = 10.dp

    Box(
        modifier = Modifier
            .clickable {
                set.id?.let {
                    navController.navigate(Screen.Cards.createRoute(set.id))
                }
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            AsyncImage(
                model = set.setImg,
                contentDescription = "Set image",
                modifier = Modifier
                    .size(setItemHeight)
            )
            Text(
                text = set.setName,
                fontSize = textSize,
                textAlign = TextAlign.Center,

            )
            IconButton(
                onClick = {
                    set.id?.let{
                        navController.navigate(Screen.Edit.createRoute(set.id))
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit set icon"
                )
            }
        }
    }
}