package com.wse.project.ui

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wse.project.networkdb.ApiService
import com.wse.project.networkdb.Datum
// Replaces ItemKeyedDataSource.
//override fun getRefreshKey(state: PagingState): String? {
//    return state.anchorPosition?.let { anchorPosition ->
//        state.getClosestItemToPosition(anchorPosition)?.id
//    }
//}

// Replacing PositionalDataSource.
//override fun getRefreshKey(state: PagingState): Int? {
//    return state.anchorPosition
//}
class PassengersDataSource(
    private val api: ApiService
) : PagingSource<Int, Datum>() {
    override fun getRefreshKey(state: PagingState<Int, Datum>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Datum> {
        return try {  val nextPageNumber = params.key ?: 0
        val response = api.getPassenger(nextPageNumber)

            LoadResult.Page(
                data = response.data,

                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalPages!!) nextPageNumber + 1 else null
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}