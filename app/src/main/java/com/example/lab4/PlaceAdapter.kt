package com.example.lab4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlaceAdapter(
    private val onPlaceClick: (Place) -> Unit,
    private val onFavoriteClick: (Place) -> Unit,
    private val isFavorite: (Place) -> Boolean
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    private var places = emptyList<Place>()

    fun updatePlaces(newPlaces: List<Place>) {
        places = newPlaces
        notifyDataSetChanged()
    }

    inner class PlaceViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val icon: TextView = itemView.findViewById(R.id.placeIcon)
        val name: TextView = itemView.findViewById(R.id.placeName)
        val description: TextView =
            itemView.findViewById(R.id.placeDescription)

        val favorite: TextView =
            itemView.findViewById(R.id.placeFavorite)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_place, parent, false)

        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PlaceViewHolder,
        position: Int
    ) {

        val place = places[position]

        holder.icon.text = place.icon
        holder.name.text = place.name
        holder.description.text = place.description

        holder.favorite.text = if (isFavorite(place)) "❤️" else "♡"

        holder.itemView.setOnClickListener {
            onPlaceClick(place)
        }

        holder.favorite.setOnClickListener {
            onFavoriteClick(place)
        }
    }

    override fun getItemCount(): Int {
        return places.size
    }
}