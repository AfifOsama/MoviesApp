package com.madman.moviesapp.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.madman.moviesapp.R
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.databinding.ItemsMoviesBinding
import com.madman.moviesapp.ui.detail.movies.DetailMovieActivity
import com.madman.moviesapp.utils.GlideHelper.API_IMG_ENDPOINT
import com.madman.moviesapp.utils.GlideHelper.ENDPOINT_IMG_SIZE_W185
import com.madman.moviesapp.utils.GlideHelper.glideImage

class MoviesAdapter(private val callback: MoviesFragmentCallback) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var listMovies = ArrayList<MoviesEntity>()

    fun setMovies(movies: List<MoviesEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val itemsMoviesBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        val course = listMovies[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MoviesEntity) {
            with(binding) {
                tvTitle.text = movies.title
                tvDescription.text = movies.description
                tvReleaseDate.text = movies.releaseDate
                glideImage(itemView.context, API_IMG_ENDPOINT + ENDPOINT_IMG_SIZE_W185 + movies.imgPosterPath, imgMovie)
                btnShare.setOnClickListener { callback.onShareClick(movies) }
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_DETAIL, movies.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}