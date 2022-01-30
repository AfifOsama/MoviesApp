package com.madman.moviesapp.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.madman.moviesapp.R
import com.madman.moviesapp.ui.favorite.movie.FavoritedMoviesFragment
import com.madman.moviesapp.ui.favorite.tvshow.FavoritedTvShowsFragment
import com.madman.moviesapp.ui.movies.MoviesFragment
import com.madman.moviesapp.ui.tvshow.TVShowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavoritedMoviesFragment()
            1 -> FavoritedTvShowsFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(
        TAB_TITLES[position]
    )

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_show)
    }
}