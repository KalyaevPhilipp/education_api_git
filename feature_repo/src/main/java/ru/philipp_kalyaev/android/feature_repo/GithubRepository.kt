package ru.philipp_kalyaev.android.feature_repo

import kotlinx.coroutines.flow.Flow


interface GithubRepository {
    suspend fun getDetails(username: String): List<User>

    suspend fun getUsers()

    suspend fun getUserById(id: Int): List<User>

    fun getUserList(): Flow<List<User>>

    suspend fun setUserList(users: List<User>)

}