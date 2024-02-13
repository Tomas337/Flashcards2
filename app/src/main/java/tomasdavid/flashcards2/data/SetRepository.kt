package tomasdavid.flashcards2.data

import javax.inject.Inject

class SetRepository @Inject constructor(
    private val setDao: SetDao
) {
    fun getAllSets(): List<SetEntity> {
        return setDao.getAllSets()
    }

    fun getSet(id: Int): SetEntity {
        return setDao.getSet(id)
    }

    fun upsertSet(setEntity: SetEntity) {
        setDao.upsertSet(setEntity)
    }

    fun deleteSet(id: Int) {
        setDao.deleteSet(id)
    }
}