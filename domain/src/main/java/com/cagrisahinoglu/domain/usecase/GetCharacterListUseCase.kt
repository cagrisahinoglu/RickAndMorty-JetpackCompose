package com.cagrisahinoglu.domain.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cagrisahinoglu.domain.model.Result
import com.cagrisahinoglu.domain.repository.CharacterRepository

class GetCharacterListUseCase(
    private val repository: CharacterRepository,
): PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val page = params.key ?: 1
            val data = repository.getCharacterList(page)
            LoadResult.Page(
                data = data.results,
                prevKey = if(data.info.prev.isNullOrBlank()) null else page.minus(1),
                nextKey = if(data.info.next.isNullOrBlank()) null else page.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}

