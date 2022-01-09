package com.madman.moviesapp.ui.detail.tvshow

import androidx.lifecycle.ViewModel
import com.madman.moviesapp.data.TVShowEntity
import com.madman.moviesapp.utils.DataDummy

class DetailTvshowViewModel : ViewModel() {
    private lateinit var tvShowName: String

    fun selectedTVshow(TVshowId: String) {
        this.tvShowName = TVshowId
    }

    fun getTVshow(): TVShowEntity {
        lateinit var tvshow: TVShowEntity
        val tvShowEntities = DataDummy.generateTVShow()
        for (TVShowEntity in tvShowEntities) {
            if (TVShowEntity.title == tvShowName) {
                tvshow = TVShowEntity
            }
        }
        return tvshow
    }
}