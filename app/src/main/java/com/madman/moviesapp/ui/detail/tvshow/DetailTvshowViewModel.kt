package com.madman.moviesapp.ui.detail.tvshow

import androidx.lifecycle.ViewModel
import com.madman.moviesapp.data.TVShowEntity
import com.madman.moviesapp.utils.DataDummy

class DetailTvshowViewModel : ViewModel() {
    private lateinit var TVshowId: String

    fun selectedTVshow(TVshowId: String) {
        this.TVshowId = TVshowId
    }

    fun getTVshow(): TVShowEntity {
        lateinit var TVshow: TVShowEntity
        val TVShowEntities = DataDummy.generateTVShow()
        for (TVShowEntity in TVShowEntities) {
            if (TVShowEntity.title == TVshowId) {
                TVshow = TVShowEntity
            }
        }
        return TVshow
    }
}