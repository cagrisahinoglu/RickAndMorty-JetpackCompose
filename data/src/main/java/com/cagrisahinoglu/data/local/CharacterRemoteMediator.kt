package com.cagrisahinoglu.data.local

import android.util.Log
import androidx.paging.*
import com.cagrisahinoglu.data.local.dao.CharacterDao
import com.cagrisahinoglu.data.model.entities.CharacterEntity
import com.cagrisahinoglu.data.model.toEntity
import com.cagrisahinoglu.data.remote.api.CharacterService

@ExperimentalPagingApi
class CharacterRemoteMediator (
    private val characterService: CharacterService,
    private val characterDao: CharacterDao
) : RemoteMediator<Int, CharacterEntity>() {

    private val initialPage = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            Log.d(CharacterRemoteMediator::class.java.simpleName, "LoadType -> $loadType")
            val currentPage = when(loadType) {
                LoadType.REFRESH -> {
                    val closestPage = getClosestItemIdToCurrentPosition(state = state)
                    closestPage?.next?.minus(1) ?: initialPage
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.APPEND -> {
                    val entity = state.lastItemOrNull()
                    Log.d(CharacterRemoteMediator::class.java.simpleName, "LastItemNext -> ${entity?.next}")
                    entity?.next ?: return MediatorResult.Success(entity != null)
                }
            }
            Log.d(CharacterRemoteMediator::class.java.simpleName, "currentPage -> $currentPage")

            val response = characterService.getCharacterList(currentPage)
            val endOfPaginationReached = response.results.size < state.config.pageSize
            if(loadType == LoadType.REFRESH) {
                characterDao.deleteAllCharacters()
            }
            characterDao.addCharacterList(
                response.results.map {
                    it.toEntity(
                        prev = if(currentPage == initialPage) null else currentPage.minus(1),
                        next = if(endOfPaginationReached) null else currentPage.plus(1)
                    )
                }
            )
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Log.d(CharacterRemoteMediator::class.java.simpleName, "Error -> $e")
            MediatorResult.Error(e)
        }
    }

    private fun getClosestItemIdToCurrentPosition(state: PagingState<Int, CharacterEntity>): CharacterEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                characterDao.getCharacterById(id)
            }
        }
    }
}