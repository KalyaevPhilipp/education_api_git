package ru.philipp_kalyaev.android.education_api_git.domain

import android.util.Log
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.philipp_kalyaev.android.education_api_git.data.Common
import ru.philipp_kalyaev.android.education_api_git.data.api.model.ResponseListUsers
import ru.philipp_kalyaev.android.education_api_git.domain.mapper.UserMapper
import ru.philipp_kalyaev.android.education_api_git.ui.details.UserListViewModel
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User
import kotlin.coroutines.CoroutineContext

class GithubRepository {
    private val retrofitService = Common.retrofitService
    var customScope = CoroutineScope(Dispatchers.IO)
    var response:List<ResponseListUsers>? = null
    fun getDetails(
        username: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<User>) -> Unit,
    ) {
        response = null
        customScope.launch {
           response = retrofitService.getSubscribersByUser(username)
                if (response != null) {
                    val apiUsers = response as List<ResponseListUsers>
                    val users = UserMapper.apiToDomain(apiUsers)
                    onSuccess(users)
                }
               /* .enqueue(object : Callback<List<ResponseListUsers>> {
                    override fun onFailure(call: Call<List<ResponseListUsers>>, t: Throwable) {
                        onError(t)
                    }

                    override fun onResponse(
                        call: Call<List<ResponseListUsers>>,
                        response: Response<List<ResponseListUsers>>
                    ) {
                        val apiUsers = response.body() as List<ResponseListUsers>
                        val users = UserMapper.apiToDomain(apiUsers)
                        onSuccess(users)
                    }
                })*/
        }
    }

    fun getUsers(
        onError: (Throwable) -> Unit,
        onSuccess: (List<User>) -> Unit,
    ) {
        response = null
        customScope.launch {
            response =  retrofitService.getUserList()
                if (response != null) {
                    val apiUsers = response as List<ResponseListUsers>
                    val users = UserMapper.apiToDomain(apiUsers)
                    onSuccess(users)
                }
                /*.enqueue(object : retrofit2.Callback<List<ResponseListUsers>> {
                    override fun onFailure(call: Call<List<ResponseListUsers>>, t: Throwable) {
                        onError(t)
                    }

                    override fun onResponse(
                        call: Call<List<ResponseListUsers>>,
                        response: Response<List<ResponseListUsers>>
                    ) {
                        val apiUsers = response.body() as List<ResponseListUsers>
                        val users = UserMapper.apiToDomain(apiUsers)
                        onSuccess(users)
                    }
                })*/
        }

    }
}
