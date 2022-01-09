package com.madman.moviesapp.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.madman.moviesapp.R
import com.madman.moviesapp.data.TVShowEntity
import com.madman.moviesapp.databinding.FragmentTVShowBinding

class TVShowFragment : Fragment(), TVShowFragmentCallback {
    private lateinit var binding: FragmentTVShowBinding
    private val viewModel: TVShowViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentTVShowBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvshow = viewModel.getTVShow()
            val tvShowAdapter = TVShowAdapter(this)
            tvShowAdapter.setTVshow(tvshow)
            with(binding) {
                progressBar.visibility = View.GONE
                rvTvshow.layoutManager = LinearLayoutManager(activity)
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