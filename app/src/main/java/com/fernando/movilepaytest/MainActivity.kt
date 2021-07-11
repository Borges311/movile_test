package com.fernando.movilepaytest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.fernando.movilepaytest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var bindViews: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(bindViews.tbMain)
        setContentView(bindViews.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment

        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        initDestinationListener()
    }

    private fun initDestinationListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.homeFragment,
                R.id.responseErrorFragment -> bindViews.tbMain.visibility = View.GONE
                else -> {
                    bindViews.tbMain.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun onPressBackArrow(navigateTo: Int) = navController.navigate(navigateTo)

 }