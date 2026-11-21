package com.example.lab4

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class CityViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val FAVORITES_KEY = "favorite_places"
    }

    val favoriteIds: LiveData<List<String>> =
        savedStateHandle.getLiveData(FAVORITES_KEY, emptyList())

    fun toggleFavorite(place: Place) {

        val currentFavorites = favoriteIds.value.orEmpty().toMutableList()

        if (currentFavorites.contains(place.id)) {
            currentFavorites.remove(place.id)
        } else {
            currentFavorites.add(place.id)
        }

        savedStateHandle[FAVORITES_KEY] = currentFavorites
    }

    fun isFavorite(place: Place): Boolean {
        return favoriteIds.value.orEmpty().contains(place.id)
    }

    fun getFavoritePlaces(): List<Place> {

        return favoriteIds.value.orEmpty().mapNotNull { id ->
            PlaceRepository.getPlaceById(id)
        }
    }
}