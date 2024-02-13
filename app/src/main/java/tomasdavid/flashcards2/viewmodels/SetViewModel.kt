package tomasdavid.flashcards2.viewmodels

import androidx.lifecycle.ViewModel
import tomasdavid.flashcards2.data.SetEntity
import tomasdavid.flashcards2.data.SetRepository
import tomasdavid.flashcards2.ui.screens.Set
import tomasdavid.flashcards2.viewmodels.Converters.setEntityToSet
import tomasdavid.flashcards2.viewmodels.Converters.setToSetEntity
import javax.inject.Inject

class SetViewModel @Inject constructor(
    private val setRepository: SetRepository
) : ViewModel() {

    fun getAllSetEntities(): List<SetEntity> {
        return setRepository.getAllSets()
    }

    fun getSet(id: Int): Set {
        val setEntity = setRepository.getSet(id)
        return setEntityToSet(setEntity)
    }

    fun upsertSet(set: Set) {
        val setEntity = setToSetEntity(set)
        setRepository.upsertSet(setEntity)
    }

    fun deleteSet(set: Set) {
        set.id?.let { setRepository.deleteSet(set.id) }
    }
}
