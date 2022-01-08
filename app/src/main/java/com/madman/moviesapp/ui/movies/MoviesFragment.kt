package com.madman.moviesapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.madman.moviesapp.R
import com.madman.moviesapp.data.MoviesEntity
import com.madman.moviesapp.databinding.FragmentMovieBinding

class MoviesFragment : Fragment(), MoviesFragmentCallback {
    private lateinit var binding: FragmentMovieBinding
    private val viewModel: MoviesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movies = viewModel.getMovies()
            val moviesAdapter = MoviesAdapter(this)
            moviesAdapter.setMovies(movies)
            with(binding) {
                progressBar.visibility = View.GONE
                rvMovies.layoutManager = LinearLayoutManager(activity)
                rvMovies.setHasFixedSize(true)
                rvMovies.adapter = moviesAdapter
            }
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