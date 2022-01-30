package com.madman.moviesapp.ui.detail.tvshow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.madman.moviesapp.R
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
        contentBinding.tbFavorite.setOnClickListener {
            if (contentBinding.tbFavorite.isChecked) {
                Toast.makeText(
                    this@DetailTvshowActivity,
                    "You've favorite this TV Show",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun populateTvshow(TVshow: TVShowEntity) {
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
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}