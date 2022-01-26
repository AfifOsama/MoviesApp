package com.madman.moviesapp.ui.tvshow

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
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.databinding.FragmentTVShowBinding
import com.madman.moviesapp.ui.movies.MoviesViewModel
import com.madman.moviesapp.viewmodel.ViewModelFactory

class TVShowFragment : Fragment(), TVShowFragmentCallback {
    private lateinit var binding: FragmentTVShowBinding
    private lateinit var  viewModel: TVShowViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentTVShowBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory= ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this,factory)[TVShowViewModel::class.java]
        if (activity != null) {
            val tvShowAdapter = TVShowAdapter(this)
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getTVShow().observe(viewLifecycleOwner, {
                binding.progressBar.visibility = View.GONE
                tvShowAdapter.setTVshow(it)
                tvShowAdapter.notifyDataSetChanged()
            })
            with(binding) {
                rvTvshow.layoutManager = LinearLayoutManager(context)
                rvTvshow.setHasFixedSize(true)
                rvTvshow.adapter = tvShowAdapter
            }
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