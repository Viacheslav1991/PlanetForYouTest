package com.example.planet.fr.you.test.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.planet.fr.you.test.R
import com.example.planet.fr.you.test.data.Repository
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val repository: Repository by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sync_mi -> {lifecycleScope.launch { repository.fillDatabaseFromServer() }}
            android.R.id.home -> navController.navigateUp()
        }
        return true
    }
}