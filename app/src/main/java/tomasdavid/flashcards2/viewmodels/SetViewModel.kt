package tomasdavid.flashcards2.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tomasdavid.flashcards2.data.SetEntity
import tomasdavid.flashcards2.data.SetRepository
import tomasdavid.flashcards2.ui.screens.Set
import tomasdavid.flashcards2.viewmodels.Converters.setEntityToSet
import tomasdavid.flashcards2.viewmodels.Converters.setToSetEntity
import javax.inject.Inject

@HiltViewModel
class SetViewModel @Inject constructor(
    private val setRepository: SetRepository
) : ViewModel() {

    suspend fun getAllSets(): List<Set> = withContext(Dispatchers.IO) {
        val setEntities = setRepository.getAllSets()
        var sets = mutableListOf<Set>()
        for (entity in setEntities) {
            sets.add(setEntityToSet(entity))
        }
        sets.toList()
    }

    suspend fun getSet(id: Int): Set = withContext(Dispatchers.IO) {
        val setEntity = setRepository.getSet(id)
        setEntityToSet(setEntity)
    }

    suspend fun upsertSet(set: Set) = withContext(Dispatchers.IO) {
        val setEntity = setToSetEntity(set)
        setRepository.upsertSet(setEntity)
    }

    suspend fun deleteSet(set: Set) = withContext(Dispatchers.IO) {
        set.id?.let { setRepository.deleteSet(set.id) }
    }
}
