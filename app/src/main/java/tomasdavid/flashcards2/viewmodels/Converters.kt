package tomasdavid.flashcards2.viewmodels

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tomasdavid.flashcards2.data.SetEntity
import tomasdavid.flashcards2.ui.screens.Card
import tomasdavid.flashcards2.ui.screens.Set

object Converters {

    @TypeConverter
    @JvmStatic
    fun displayOrderListToJson(cards: List<List<String>>): String {
        return Gson().toJson(cards)
    }

    @TypeConverter
    @JvmStatic
    fun jsonToDisplayOrderList(json: String): List<List<String>> {
        val listType = object : TypeToken<List<List<String>>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    @JvmStatic
    fun cardListToJson(cards: MutableList<Card>): String {
        return Gson().toJson(cards)
    }

    @TypeConverter
    @JvmStatic
    fun jsonToCardList(json: String): MutableList<Card> {
        val listType = object : TypeToken<MutableList<Card>>() {}.type
        return Gson().fromJson(json, listType)
    }

    fun setEntityToSet(setEntity: SetEntity): Set {
        val cards = Converters.jsonToCardList(setEntity.cards)
        val displayOrder = Converters.jsonToDisplayOrderList(setEntity.displayOrder)

        return Set(
            id = setEntity.id,
            setName = setEntity.setName,
            setImg = setEntity.setImg,
            cards = cards,
            displayOrder = displayOrder
        )
    }

    fun setToSetEntity(set: Set): SetEntity {
        val stringifiedCards = Converters.cardListToJson(set.cards)
        val stringifiedDisplayOrder = Converters.displayOrderListToJson(set.displayOrder)

        return SetEntity(
            id = set.id,
            setName = set.setName,
            setImg = set.setImg,
            cards = stringifiedCards,
            displayOrder = stringifiedDisplayOrder
        )
    }
}