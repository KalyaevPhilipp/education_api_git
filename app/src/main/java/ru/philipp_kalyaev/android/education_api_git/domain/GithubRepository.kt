package ru.philipp_kalyaev.android.education_api_git.domain

import kotlinx.coroutines.*
import ru.philipp_kalyaev.android.education_api_git.data.Common
import ru.philipp_kalyaev.android.education_api_git.data.api.RetrofitService
import ru.philipp_kalyaev.android.education_api_git.data.api.model.ResponseListUsers
import ru.philipp_kalyaev.android.education_api_git.domain.mapper.UserMapper
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User
import javax.inject.Inject

/*interface GithubRepository {
    suspend fun getDetails(
        username: String,
    ): List<User>

    fun getUsers(
        onError: (Throwable) -> Unit,
        onSuccess: (List<User>) -> Unit,
    )
}*/

class GithubRepository @Inject constructor(
    private val retrofitService: RetrofitService,
) {
    suspend fun getDetails(
        username: String,
    ): List<User> {
        val response = retrofitService.getSubscribersByUser(username)
        return UserMapper.apiToDomain(response)
    }

    suspend fun getUsers():List<User> {
        val response = retrofitService.getUserList()
        return UserMapper.apiToDomain(response)
    }

}