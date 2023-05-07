package com.zeyadmaamoun.android.e_commerceapp.fragments.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.zeyadmaamoun.android.e_commerceapp.R
import com.zeyadmaamoun.android.e_commerceapp.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        installSplashScreen()

        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.auth_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        return
    }
}