package tomasdavid.flashcards2.ui.screens.editscreen

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.round
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import tomasdavid.flashcards2.navigation.Screen
import tomasdavid.flashcards2.viewmodels.SetViewModel
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalLayoutApi::class
)
@Composable
fun EditScreen(navController: NavController) {
    val scrollBehavior = pinnedScrollBehavior()
    var showMenu by remember { mutableStateOf(false) }
    var displayOrderExpanded by remember { mutableStateOf(false) }
    var cardsExpanded by remember { mutableStateOf(false) }

    var expandedItemId by remember { mutableStateOf<Int?>(null) }
    var expandedItemPosition by remember { mutableStateOf<Position?>(null) }

    val scope = rememberCoroutineScope()
    val setViewModel: SetViewModel = hiltViewModel()

    var setName = "New set"

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val down = awaitFirstDown(pass = PointerEventPass.Initial)
                        val downTime = System.currentTimeMillis()
                        val downPosition = down.position

                        val event = awaitPointerEvent(pass = PointerEventPass.Initial)
                        val upTime = System.currentTimeMillis()

                        val tapTimeout = viewConfiguration.longPressTimeoutMillis

                        val offset = event.changes[0].position
                        val isTap = upTime - downTime < tapTimeout &&
                                event.changes.size == 1 &&
                                (offset - downPosition).getDistance() < viewConfiguration.touchSlop

                        if (isTap && expandedItemId != null && !clickedOnExpandedItem(
                                offset,
                                expandedItemPosition
                            )
                        ) {
                            expandedItemId = null
                            expandedItemPosition = null
                            down.consume()
                            event.changes[0].consume()
                        }
                    }
                }
            },
        topBar = {
            TopAppBar(
                title = {
                    BasicTextField(
                        value = setName,
                        onValueChange = { setName = it }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.MAIN.name) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Return to main screen",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Show options for the edit screen",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        modifier = Modifier
                    ) {
                        DropdownMenuItem(
                            text = { Text("Import csv") },
                            onClick = { /*TODO*/ }
                        )
                        DropdownMenuItem(
                            text = { Text("Delete") },
                            onClick = { /*TODO*/ }
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            displayOrderExpandable(
                expanded = displayOrderExpanded,
                onToggle = { displayOrderExpanded = !displayOrderExpanded }
            )
            cardsExpandable(
                expanded = cardsExpanded,
                expandToggle = { cardsExpanded = !cardsExpanded },
                expandedItemId = expandedItemId,
                setExpandedItemId = { itemId: Int? -> expandedItemId = itemId },
                setExpandedItemPosition = { itemPosition: Position? ->
                    expandedItemPosition = itemPosition
                },
                setViewModel = setViewModel,
                scope = scope
            )
        }
    }
}

fun clickedOnExpandedItem(offset: Offset, position: Position?): Boolean {
    if (position == null) {
        return false
    }
    return (offset.round().x in position.x..(position.x + position.width) &&
            offset.round().y in position.y..(position.y + position.height))
}