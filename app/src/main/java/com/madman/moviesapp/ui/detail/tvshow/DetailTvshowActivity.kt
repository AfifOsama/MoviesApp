package com.madman.moviesapp.ui.detail.tvshow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.databinding.ActivityDetailTvshowBinding
import com.madman.moviesapp.databinding.ContentDetailTvshowBinding
import com.madman.moviesapp.utils.GlideHelper
import com.madman.moviesapp.viewmodel.ViewModelFactory

class DetailTvshowActivity : AppCompatActivity() {
    private lateinit var contentBinding: ContentDetailTvshowBinding
    private lateinit var tvShowViewModel: DetailTvshowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val detailBinding = ActivityDetailTvshowBinding.inflate(layoutInflater)
        contentBinding = detailBinding.detailContent
        setContentView(detailBinding.root)

        setSupportActionBar(detailBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initiateUI()
    }

    private fun initiateUI() {
        val factory = ViewModelFactory.getInstance(this)
        tvShowViewModel = ViewModelProvider(this, factory)[DetailTvshowViewModel::class.java]
        val extrasId = intent.getIntExtra(EXTRA_DETAIL, 0)
        contentBinding.progressBar.visibility = View.VISIBLE
        tvShowViewModel.getTvShow(extrasId).observe(this, {
            contentBinding.progressBar.visibility = View.GONE
            populateTvshow(it)
        })
    }

    private fun populateTvshow(TVshow: TVShowEntity) {
        val favStatus = TVshow.isFavorite
        with(contentBinding) {
            tvTitle.text = TVshow.title
            tvDescription.text = TVshow.description
            tvScore.text = TVshow.score.toString()
            tvReleaseDate.text = TVshow.releaseDate
            GlideHelper.glideImage(
                this@DetailTvshowActivity,
                GlideHelper.API_IMG_ENDPOINT + GlideHelper.ENDPOINT_IMG_SIZE_W185 + TVshow.imgPosterPath,
                imgPoster
            )
            GlideHelper.glideImage(
                this@DetailTvshowActivity,
                GlideHelper.API_IMG_ENDPOINT + GlideHelper.ENDPOINT_IMG_SIZE_W780 + TVshow.imgPreviewPath,
                imgMovie
            )
            tbFavorite.setOnClickListener {
                setFavoriteTvShow(TVshow)
            }
            contentBinding.tbFavorite.isChecked = favStatus
        }
    }

    private fun setFavoriteTvShow(TVshow: TVShowEntity?) {
        if (TVshow != null) {
            if (TVshow.isFavorite) {
                Toast.makeText(
                    this@DetailTvshowActivity,
                    "You've unfavorite this TV Show",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@DetailTvshowActivity,
                    "You've favorite this TV Show",
                    Toast.LENGTH_SHORT
                ).show()
            }
            tvShowViewModel.setFavoriteTvShow(TVshow)
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}