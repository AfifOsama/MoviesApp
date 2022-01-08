package com.madman.moviesapp.ui.detail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.madman.moviesapp.R
import com.madman.moviesapp.databinding.ActivityDetailBinding
import com.madman.moviesapp.databinding.ContentDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var contentBinding: ContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        setSupportActionBar(detailBinding.toolbar)

        contentBinding=detailBinding.detailContent
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initiateUI()
    }

    private fun initiateUI() {
        with(contentBinding){
            if(tbFavorite.isChecked){
                !tbFavorite.isChecked
            }
        }
    }

    companion object{
        const val EXTRA_DETAIL="extra_detail"
    }
}