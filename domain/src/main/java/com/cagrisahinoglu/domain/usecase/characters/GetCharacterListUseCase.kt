package com.cagrisahinoglu.domain.usecase.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cagrisahinoglu.domain.model.Character
import com.cagrisahinoglu.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharacterListUseCase(
    private val repository: CharacterRepository,
): PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val data = repository.getCharacterList(page)
            data.characters.map {
                it.isFav = repository.checkIsCharacterFavorite(it.id).isNotEmpty()
            }
            LoadResult.Page(
                data = data.characters,
                prevKey = if(data.info.prev.isNullOrBlank()) null else page.minus(1),
                nextKey = if(data.info.next.isNullOrBlank()) null else page.plus(1)
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}

