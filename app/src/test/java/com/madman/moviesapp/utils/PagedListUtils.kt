package com.madman.moviesapp.utils

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

object PagedListUtils {
    fun <T : Any> mockPagedList(list: List<T>?): PagedList<T> {
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        Mockito.`when`(pagedList[ArgumentMatchers.anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list?.get(index)
        }
        Mockito.`when`(pagedList.size).thenReturn(list?.size)

        return pagedList
    }
}