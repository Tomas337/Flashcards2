package tomasdavid.flashcards2.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import tomasdavid.flashcards2.navigation.AppNavHost
import tomasdavid.flashcards2.ui.screens.Set
import tomasdavid.flashcards2.ui.theme.Flashcards2Theme
import tomasdavid.flashcards2.viewmodels.SetViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // load db with testing data
            val setViewModel: SetViewModel = hiltViewModel()
            LaunchedEffect(key1 = true) {
                val sets = setViewModel.getAllSets()
                val range = 10 - sets.size

                for (i in 0..range) {
                    setViewModel.upsertSet(
                        Set(setName = "Set $i")
                    )
                }
            }

            Flashcards2Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}