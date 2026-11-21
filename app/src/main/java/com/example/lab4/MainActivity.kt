
package com.example.lab4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Получаем элементы интерфейса
        drawerLayout = findViewById(R.id.drawerLayout)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)

        val bottomNavigation =
            findViewById<BottomNavigationView>(R.id.bottomNavigation)

        val navigationView =
            findViewById<NavigationView>(R.id.navigationView)

        // Получаем NavController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment

        navController = navHostFragment.navController

        // Настраиваем Toolbar и Drawer
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.categoryFragment,
                R.id.aboutFragment
            ),
            drawerLayout
        )

        NavigationUI.setupWithNavController(
            toolbar,
            navController,
            appBarConfiguration
        )

        // Настраиваем нижнюю навигацию
        NavigationUI.setupWithNavController(
            bottomNavigation,
            navController
        )

        // Обрабатываем боковое меню
        navigationView.setNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.menuHome -> {
                    navController.navigate(R.id.homeFragment)
                }

                R.id.menuAttractions -> {
                    openCategory("attractions")
                }

                R.id.menuCafes -> {
                    openCategory("cafes")
                }

                R.id.menuParks -> {
                    openCategory("parks")
                }

                R.id.menuEntertainment -> {
                    openCategory("entertainment")
                }

                R.id.menuFavorites -> {
                    openInfo("favorites")
                }

                R.id.menuSettings -> {
                    openInfo("settings")
                }

                R.id.menuAbout -> {
                    openInfo("about")
                }

                else -> return@setNavigationItemSelectedListener false
            }

            drawerLayout.closeDrawers()
            true
        }

        // Синхронизация нижней навигации с текущим экраном
        navController.addOnDestinationChangedListener { _, destination, _ ->

            bottomNavigation.menu.findItem(destination.id)?.isChecked = true

        }
    }

    // Открытие выбранной категории
    private fun openCategory(categoryId: String) {

        navController.navigate(
            R.id.categoryFragment,
            bundleOf("categoryId" to categoryId)
        )
    }

    // Открытие информации, избранного или настроек
    private fun openInfo(infoType: String) {

        navController.navigate(
            R.id.aboutFragment,
            bundleOf("infoType" to infoType)
        )
    }

    // Обработка кнопки Назад
    override fun onSupportNavigateUp(): Boolean {

        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }
}
