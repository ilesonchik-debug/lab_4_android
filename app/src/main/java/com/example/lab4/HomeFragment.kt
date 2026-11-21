package com.example.lab4

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val context = requireContext()

        // Основной прокручиваемый контейнер
        val scrollView = ScrollView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            isFillViewport = true
        }

        // Содержимое главной страницы
        val content = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(24, 24, 24, 32)
        }

        // Заголовок
        val title = TextView(context).apply {
            text = "Санкт-Петербург"
            textSize = 28f
            gravity = Gravity.CENTER
            setPadding(0, 16, 0, 12)
        }

        val subtitle = TextView(context).apply {
            text = "Выберите категорию"
            textSize = 18f
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 24)
        }

        content.addView(title)
        content.addView(subtitle)

        // Категории
        addCategoryButton(content, "достопримечательности", "attractions")
        addCategoryButton(content, "кафе и рестораны", "cafes")
        addCategoryButton(content, "парки", "parks")
        addCategoryButton(content, "развлечения", "entertainment")

        scrollView.addView(content)

        return scrollView
    }

    private fun addCategoryButton(
        container: LinearLayout,
        buttonText: String,
        categoryId: String
    ) {
        val button = Button(requireContext()).apply {
            text = buttonText
            textSize = 16f
            isAllCaps = false
            minHeight = 64
        }

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 8, 0, 8)
        }

        container.addView(button, params)

        button.setOnClickListener {
            findNavController().navigate(
                R.id.categoryFragment,
                bundleOf("categoryId" to categoryId)
            )
        }
    }
}
