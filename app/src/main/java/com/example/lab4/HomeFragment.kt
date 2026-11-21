package com.example.lab4

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.core.os.bundleOf

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val context = requireContext()

        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(24, 24, 24, 24)
        }

        val title = TextView(context).apply {
            text = "Санкт-Петербург"
            textSize = 28f
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(context).apply {
            text = "Куда отправимся сегодня?"
            textSize = 18f
            gravity = Gravity.CENTER
            setPadding(0, 16, 0, 24)
        }

        layout.addView(title)
        layout.addView(subtitle)

        val categories = listOf(
            "Достопримечательности" to "attractions",
            "Кафе и рестораны" to "cafes",
            "Парки" to "parks",
            "Развлечения" to "entertainment"
        )

        categories.forEach { (name, id) ->

            val button = Button(context).apply {
                text = name
                textSize = 16f

                setOnClickListener {
                    findNavController().navigate(
                        R.id.categoryFragment,
                        bundleOf("categoryId" to id)
                    )
                }
            }

            layout.addView(
                button,
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 12
                }
            )
        }

        return layout
    }
}