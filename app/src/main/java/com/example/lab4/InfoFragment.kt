package com.example.lab4

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfoFragment : Fragment() {

    private val cityViewModel: CityViewModel by activityViewModels()

    private lateinit var adapter: PlaceAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var contentText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val context = requireContext()

        val infoType = arguments?.getString("infoType") ?: "about"

        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 24, 20, 20)
        }

        val title = TextView(context).apply {
            textSize = 26f
            gravity = Gravity.CENTER
            setPadding(0, 12, 0, 24)

            text = when (infoType) {
                "favorites" -> "избранное"
                "settings" -> "настройки"
                else -> "инфа"
            }
        }

        contentText = TextView(context).apply {
            textSize = 17f
            gravity = Gravity.CENTER
        }

        recyclerView = RecyclerView(context).apply {
            layoutManager = LinearLayoutManager(context)
        }

        adapter = PlaceAdapter(
            onPlaceClick = { place ->

                findNavController().navigate(
                    R.id.detailFragment,
                    bundleOf(
                        "placeName" to place.name,
                        "placeDescription" to place.description,
                        "placeCategory" to place.category
                    )
                )
            },
            onFavoriteClick = { place ->
                cityViewModel.toggleFavorite(place)
            },
            isFavorite = { place ->
                cityViewModel.isFavorite(place)
            }
        )

        recyclerView.adapter = adapter

        layout.addView(title)

        when (infoType) {

            "favorites" -> {

                layout.addView(
                    recyclerView,
                    LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        0,
                        1f
                    )
                )
            }

            "settings" -> {

                contentText.text =
                    "настройки приложения\n\n" +
                            "город: Санкт-Петербург\n\n" +
                            "ну настройки есть, но настроек нет" +

                layout.addView(contentText)
            }

            else -> {

                contentText.text =
                    "Санкт-Петербург — один из красивейших городов России.\n\n" +
                            "Город основан Петром I в 1703 году.\n\n" +
                            "Здесь находятся знаменитые музеи, дворцы, парки " +
                            "и архитектурные памятники."

                layout.addView(contentText)
            }
        }

        return layout
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        cityViewModel.favoriteIds.observe(
            viewLifecycleOwner,
            Observer {

                if (arguments?.getString("infoType") == "favorites") {

                    adapter.updatePlaces(
                        cityViewModel.getFavoritePlaces()
                    )
                }
            }
        )
    }
}