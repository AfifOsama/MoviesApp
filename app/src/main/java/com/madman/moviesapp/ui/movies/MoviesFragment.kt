package com.madman.moviesapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.madman.moviesapp.R
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.databinding.FragmentMovieBinding
import com.madman.moviesapp.viewmodel.ViewModelFactory

class MoviesFragment : Fragment(), MoviesFragmentCallback {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MoviesViewModel
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
            val factory=ViewModelFactory.getInstance()
            viewModel = ViewModelProvider(this,factory)[MoviesViewModel::class.java]

            val moviesAdapter = MoviesAdapter(this)

            binding.progressBar.visibility = View.VISIBLE
            viewModel.getMovies().observe(viewLifecycleOwner, {
                binding.progressBar.visibility = View.GONE
                moviesAdapter.setMovies(it)
                moviesAdapter.notifyDataSetChanged()
            })

            with(binding) {
                rvMovies.layoutManager = LinearLayoutManager(context)
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