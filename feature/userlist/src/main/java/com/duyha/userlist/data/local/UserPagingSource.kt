package com.duyha.userlist.data.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.duyha.data.network.api.GithubApi
import com.duyha.data.network.model.toEntity
import com.duyha.domain.entity.User
import javax.inject.Inject

/**
 * @author: DuyHa
 * @date: 29/08/2024
 */
class UserPagingSource @Inject constructor(
    private val githubApi: GithubApi,
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val currentPage = params.key ?: 0
            val users = githubApi.getUsers(
                page = currentPage,
                perPage = params.loadSize
            )
            LoadResult.Page(
                data = users.map { it.toEntity() },
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (users.isEmpty()) null else currentPage + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}