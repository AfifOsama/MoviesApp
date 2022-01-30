package com.madman.moviesapp.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.madman.moviesapp.R
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.databinding.FragmentFavoritedMoviesBinding
import com.madman.moviesapp.ui.movies.MoviesAdapter
import com.madman.moviesapp.ui.movies.MoviesFragmentCallback
import com.madman.moviesapp.viewmodel.ViewModelFactory

class FavoritedMoviesFragment : Fragment(), MoviesFragmentCallback {
    private lateinit var viewModel: FavoriteMoviesViewModel
    private lateinit var binding: FragmentFavoritedMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritedMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteMoviesViewModel::class.java]
        binding.progressBar.visibility=View.VISIBLE
        viewModel.getListFavoriteMovies().observe(viewLifecycleOwner, {
            if (it != null) {
                binding.progressBar.visibility=View.GONE
                binding.rvMovies.adapter?.let { adapter ->
                    when (adapter) {
                        is MoviesAdapter -> {
                            binding.rvMovies.visibility = View.VISIBLE
                            adapter.submitList(it)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
        binding.apply {
            rvMovies.layoutManager = LinearLayoutManager(context)
            rvMovies.adapter = MoviesAdapter(this@FavoritedMoviesFragment)
        }
    }

    override fun onShareClick(movies: MoviesEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("share this Film.")
                .setText(resources.getString(R.string.share_text, movies.title))
                .startChooser()
        }
    }
}
