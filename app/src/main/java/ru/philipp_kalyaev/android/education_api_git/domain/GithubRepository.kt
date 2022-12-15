package ru.philipp_kalyaev.android.education_api_git.domain

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.philipp_kalyaev.android.education_api_git.data.Common
import ru.philipp_kalyaev.android.education_api_git.data.api.RetrofitService
import ru.philipp_kalyaev.android.education_api_git.data.api.model.ResponseListUsers
import ru.philipp_kalyaev.android.education_api_git.data.room.UserDao
import ru.philipp_kalyaev.android.education_api_git.domain.mapper.UserMapper
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User
import javax.inject.Inject


class GithubRepository @Inject constructor(
    private val retrofitService: RetrofitService,
    private val userDao: UserDao,
) {
    suspend fun getDetails(
        username: String,
    ): List<User> {
        val response = retrofitService.getSubscribersByUser(username)
        return UserMapper.apiToDomain(response)
    }

    suspend fun getUsers() {
        val response = retrofitService.getUserList()
        val mapped =  UserMapper.apiToDomain(response)
        for (user in mapped) {
            userDao.deleteUser(UserMapper.domainToDbSingle(user))
        }
        userDao.createUser(UserMapper.domainToDb(mapped))
    }
    suspend fun getUserById(id: Int): List<User> {
        val response = userDao.getUser(id)
        return UserMapper.dbToDomain(response)
    }

    fun getUserList(): Flow<List<User>> {
        return userDao.getUsers().map {
            UserMapper.dbToDomain(it)
        }
    }

    suspend fun setUserList(users: List<User>) {
        userDao.createUser(UserMapper.domainToDb(users))
    }

}

