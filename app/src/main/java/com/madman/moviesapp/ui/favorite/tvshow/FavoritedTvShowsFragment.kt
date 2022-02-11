package com.madman.moviesapp.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.madman.moviesapp.R
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.databinding.FragmentFavoritedTvShowsBinding
import com.madman.moviesapp.ui.tvshow.TVShowAdapter
import com.madman.moviesapp.ui.tvshow.TVShowFragmentCallback
import com.madman.moviesapp.viewmodel.ViewModelFactory

class FavoritedTvShowsFragment : Fragment(), TVShowFragmentCallback {
    private lateinit var viewModel: FavoriteTvShowsViewModel
    private lateinit var binding: FragmentFavoritedTvShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritedTvShowsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteTvShowsViewModel::class.java]
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getListFavoriteTvShows().observe(viewLifecycleOwner, {
            if (it != null) {
                binding.progressBar.visibility = View.GONE
                binding.rvFavTvshow.adapter?.let { adapter ->
                    when (adapter) {
                        is TVShowAdapter -> {
                            binding.rvFavTvshow.visibility = View.VISIBLE
                            adapter.submitList(it)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
        binding.apply {
            rvFavTvshow.layoutManager = LinearLayoutManager(context)
            rvFavTvshow.adapter = TVShowAdapter(this@FavoritedTvShowsFragment)
        }
    }

    override fun onShareClick(TVshow: TVShowEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("share this TV Show.")
                .setText(resources.getString(R.string.share_text, TVshow.title))
                .startChooser()
        }
    }
}