package ru.philipp_kalyaev.android.education_api_git.domain

import ru.philipp_kalyaev.android.education_api_git.data.room.UserDao
import ru.philipp_kalyaev.android.education_api_git.domain.mapper.UserMapper
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User
import javax.inject.Inject

class DbRepository @Inject constructor(
    private val userDao: UserDao,
) {
    suspend fun getUserById(id: Int): List<User> {
        val response = userDao.getUser(id)
        return UserMapper.dbToDomain(response)
    }

    suspend fun getUserList(): List<User> {
        val response = userDao.getUsers()
        return UserMapper.dbToDomain(response)
    }

    suspend fun setUserList(users:List<User>){
      for (user in users)
          userDao.createUser(UserMapper.domainToDb(listOf(user))[0])
    }

}