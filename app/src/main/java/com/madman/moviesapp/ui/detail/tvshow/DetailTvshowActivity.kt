package com.madman.moviesapp.ui.detail.tvshow

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.madman.moviesapp.R
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.databinding.ActivityDetailTvshowBinding
import com.madman.moviesapp.databinding.ContentDetailTvshowBinding

class DetailTvshowActivity : AppCompatActivity() {
    private lateinit var contentBinding: ContentDetailTvshowBinding
    private val viewModel: DetailTvshowViewModel by viewModels()

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
        val extras = intent.extras
        if (extras != null) {
            val tvshowName = extras.getString(EXTRA_DETAIL)
            if (tvshowName != null) {
                viewModel.selectedTVshow(tvshowName)
                populateTvshow(viewModel.getTVshow())
            }
        }
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
            tvDirector.text = TVshow.director
            tvGenre.text = TVshow.genre
            tvRating.text = TVshow.rating
            tvScore.text = TVshow.score
            tvReleaseDate.text = TVshow.releaseDate
            Glide.with(this@DetailTvshowActivity)
                .load(TVshow.imgPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(imgMovie)
            Glide.with(this@DetailTvshowActivity)
                .load(TVshow.imgPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(imgPoster)
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}