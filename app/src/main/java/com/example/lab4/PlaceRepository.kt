package com.example.lab4

data class Place(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val icon: String
)

object PlaceRepository {

    private val places = listOf(

        // ДОСТОПРИМЕЧАТЕЛЬНОСТИ

        Place(
            "hermitage",
            "Эрмитаж",
            "Один из крупнейших музеев мира, расположенный в Зимнем дворце.",
            "attractions",
            "🏛️"
        ),

        Place(
            "isaac",
            "Исаакиевский собор",
            "Знаменитый архитектурный памятник Санкт-Петербурга.",
            "attractions",
            "⛪"
        ),

        Place(
            "fortress",
            "Петропавловская крепость",
            "Историческое сердце Санкт-Петербурга.",
            "attractions",
            "🏰"
        ),

        Place(
            "kazan",
            "Казанский собор",
            "Красивый собор на Невском проспекте.",
            "attractions",
            "⛪"
        ),

        Place(
            "blood",
            "Спас на Крови",
            "Храм с уникальной мозаичной отделкой.",
            "attractions",
            "🏛️"
        ),

        // КАФЕ И РЕСТОРАНЫ

        Place(
            "singer",
            "Зингеръ",
            "Кафе с прекрасным видом на Казанский собор.",
            "cafes",
            "☕"
        ),

        Place(
            "bushe",
            "Буше",
            "Пекарня с большим выбором свежей выпечки.",
            "cafes",
            "🥐"
        ),

        Place(
            "happiness",
            "Счастье",
            "Уютное кафе с разнообразными десертами.",
            "cafes",
            "🍰"
        ),

        Place(
            "ceh85",
            "Цех 85",
            "Кофейня с авторскими напитками.",
            "cafes",
            "☕"
        ),

        Place(
            "marketplace",
            "Marketplace",
            "Ресторан с разнообразной кухней.",
            "cafes",
            "🍽️"
        ),

        // ПАРКИ

        Place(
            "summer",
            "Летний сад",
            "Исторический парк с фонтанами и скульптурами.",
            "parks",
            "🌳"
        ),

        Place(
            "victory",
            "Парк Победы",
            "Большой парк для прогулок и отдыха.",
            "parks",
            "🌲"
        ),

        Place(
            "elagin",
            "Елагин остров",
            "Живописное место для прогулок на природе.",
            "parks",
            "🏞️"
        ),

        Place(
            "tavrichesky",
            "Таврический сад",
            "Тихий парк с прудами и зелёными аллеями.",
            "parks",
            "🌿"
        ),

        Place(
            "mikhailovsky",
            "Михайловский сад",
            "Зелёная зона рядом с Русским музеем.",
            "parks",
            "🌳"
        ),

        // РАЗВЛЕЧЕНИЯ

        Place(
            "planetarium",
            "Планетарий",
            "Познакомьтесь с удивительным миром космоса.",
            "entertainment",
            "🔭"
        ),

        Place(
            "oceanarium",
            "Океанариум",
            "Увлекательное путешествие в подводный мир.",
            "entertainment",
            "🐠"
        ),

        Place(
            "divo",
            "Диво Остров",
            "Парк аттракционов для всей семьи.",
            "entertainment",
            "🎢"
        ),

        Place(
            "zoo",
            "Ленинградский зоопарк",
            "Один из старейших зоопарков России.",
            "entertainment",
            "🦁"
        ),

        Place(
            "mariinsky",
            "Мариинский театр",
            "Знаменитый театр оперы и балета.",
            "entertainment",
            "🎭"
        )
    )

    fun getPlaces(categoryId: String): List<Place> {
        return places.filter { it.category == categoryId }
    }

    fun getPlaceById(placeId: String): Place? {
        return places.find { it.id == placeId }
    }

    fun getAllPlaces(): List<Place> {
        return places
    }
}