package com.madman.moviesapp.ui.tvshow

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.databinding.ItemsMoviesBinding
import com.madman.moviesapp.ui.detail.tvshow.DetailTvshowActivity
import com.madman.moviesapp.utils.GlideHelper

class TVShowAdapter(private val callback: TVShowFragmentCallback) :
    PagedListAdapter<TVShowEntity, TVShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowAdapter.ViewHolder {
        val itemsMoviesBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: TVShowAdapter.ViewHolder, position: Int) {
        val tvshow = getItem(position)
        if (tvshow != null) {
            holder.bind(tvshow)
        }
    }

    inner class ViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(TVshow: TVShowEntity) {
            with(binding) {
                tvTitle.text = TVshow.title
                tvReleaseDate.text = TVshow.releaseDate
                tvDescription.text = TVshow.description
                GlideHelper.glideImage(
                    itemView.context,
                    GlideHelper.API_IMG_ENDPOINT + GlideHelper.ENDPOINT_IMG_SIZE_W185 + TVshow.imgPosterPath,
                    imgMovie
                )
                btnShare.setOnClickListener { callback.onShareClick(TVshow) }
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvshowActivity::class.java)
                    intent.putExtra(DetailTvshowActivity.EXTRA_DETAIL, TVshow.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShowEntity>() {
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}