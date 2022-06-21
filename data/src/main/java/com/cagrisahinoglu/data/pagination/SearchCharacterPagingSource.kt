package com.cagrisahinoglu.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cagrisahinoglu.data.model.toDomain
import com.cagrisahinoglu.data.remote.api.CharacterService
import com.cagrisahinoglu.domain.model.Character

class SearchCharacterPagingSource(
    private val characterService: CharacterService,
    private val searchText: String
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val data = characterService.searchCharacters(
                page = page,
                name = searchText
            ).toDomain()
            LoadResult.Page(
                data = data.characters,
                prevKey = if (data.info.prev.isNullOrBlank()) null else page.minus(1),
                nextKey = if (data.info.next.isNullOrBlank()) null else page.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}