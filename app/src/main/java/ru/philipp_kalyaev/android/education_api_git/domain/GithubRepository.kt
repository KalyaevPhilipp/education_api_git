package ru.philipp_kalyaev.android.education_api_git.domain

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.philipp_kalyaev.android.education_api_git.data.api.RetrofitService
import ru.philipp_kalyaev.android.education_api_git.data.room.UserDao
import ru.philipp_kalyaev.android.education_api_git.domain.mapper.UserMapper
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User
import javax.inject.Inject


class GithubRepository @Inject constructor(
    private val retrofitService: RetrofitService,
    private val userDao: UserDao,
) {
    fun getUsers(): Single<List<User>> {
        return retrofitService.getUserList()
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                val mapped = UserMapper.apiToDomain(it)
                for (user in mapped) {
                    userDao.deleteUser(UserMapper.domainToDbSingle(user))
                }
                userDao.createUser(UserMapper.domainToDb(mapped))
            }
            .map(UserMapper::apiToDomain)
    }

    fun getDetailsByUser(
        username: String,
    ): Single<List<User>> {
        return retrofitService.getSubscribersByUser(username).map {
            UserMapper.apiToDomain(it)
        }

    }

    fun getUserList(): Single<List<User>> {
        return userDao.getUsers().map {
            UserMapper.dbToDomain(it)
        }
    }


}

