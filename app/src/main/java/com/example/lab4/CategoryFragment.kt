package com.example.lab4

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryFragment : Fragment() {

    private val cityViewModel: CityViewModel by activityViewModels()

    private lateinit var adapter: PlaceAdapter

    private var categoryId: String = "attractions"

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        categoryId = arguments?.getString("categoryId") ?: "attractions"

        adapter = PlaceAdapter(
            onPlaceClick = { place ->

                val bundle = bundleOf(
                    "placeName" to place.name,
                    "placeDescription" to place.description,
                    "placeCategory" to place.category
                )

                findNavController().navigate(
                    R.id.detailFragment,
                    bundle
                )
            },

            onFavoriteClick = { place ->
                cityViewModel.toggleFavorite(place)
            },

            isFavorite = { place ->
                cityViewModel.isFavorite(place)
            }
        )

        return RecyclerView(requireContext()).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CategoryFragment.adapter
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        cityViewModel.favoriteIds.observe(viewLifecycleOwner) {

            val places = if (categoryId == "favorites") {
                cityViewModel.getFavoritePlaces()
            } else {
                PlaceRepository.getPlaces(categoryId)
            }

            adapter.updatePlaces(places)
        }
    }
}