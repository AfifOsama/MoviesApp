package com.madman.moviesapp.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.madman.moviesapp.R
import com.madman.moviesapp.data.TVShowEntity
import com.madman.moviesapp.databinding.ItemsMoviesBinding
import com.madman.moviesapp.ui.detail.tvshow.DetailTvshowActivity

class TVShowAdapter(private val callback: TVShowFragmentCallback) :
    RecyclerView.Adapter<TVShowAdapter.ViewHolder>() {

    private var listTVshow = ArrayList<TVShowEntity>()

    fun setTVshow(TVshow: List<TVShowEntity>?) {
        if (TVshow == null) return
        this.listTVshow.clear()
        this.listTVshow.addAll(TVshow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowAdapter.ViewHolder {
        val itemsMoviesBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: TVShowAdapter.ViewHolder, position: Int) {
        val TVshow = listTVshow[position]
        holder.bind(TVshow)
    }

    override fun getItemCount(): Int = listTVshow.size

    inner class ViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(TVshow: TVShowEntity) {
            with(binding) {
                tvTitle.text = TVshow.title
                tvReleaseDate.text = TVshow.releaseDate
                tvDescription.text = TVshow.description
                Glide.with(itemView.context)
                    .load(TVshow.imgPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgMovie)
                btnShare.setOnClickListener { callback.onShareClick(TVshow) }
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvshowActivity::class.java)
                    intent.putExtra(DetailTvshowActivity.EXTRA_DETAIL, TVshow.title)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}