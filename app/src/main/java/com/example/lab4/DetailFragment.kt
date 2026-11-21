package com.example.lab4

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val context = requireContext()

        val name = arguments?.getString("placeName") ?: "Название места"

        val description = arguments?.getString("placeDescription")
            ?: "Описание отсутствует"

        val category = arguments?.getString("placeCategory")
            ?: "Категория"

        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(24, 24, 24, 24)
        }

        val title = TextView(context).apply {
            text = name
            textSize = 28f
            gravity = Gravity.CENTER
        }

        val categoryText = TextView(context).apply {
            text = category
            textSize = 16f
            gravity = Gravity.CENTER
            setPadding(0, 16, 0, 24)
        }

        val descriptionText = TextView(context).apply {
            text = description
            textSize = 18f
            gravity = Gravity.CENTER
        }

        layout.addView(title)
        layout.addView(categoryText)
        layout.addView(descriptionText)

        return layout
    }
}