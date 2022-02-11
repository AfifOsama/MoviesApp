package com.madman.moviesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.madman.moviesapp.ui.home.HomeActivity
import splitties.activities.start

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start<HomeActivity>()
        finish()
    }
}