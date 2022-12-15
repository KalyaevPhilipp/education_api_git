package ru.philipp_kalyaev.android.education_api_git.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.philipp_kalyaev.android.education_api_git.data.api.model.ResponseListUsers

interface RetrofitService {

    @GET("/users")
    suspend fun getUserList(): List<ResponseListUsers>

    @GET("/users/{login}/followers")
    suspend fun getSubscribersByUser(@Path("login") username: String): List<ResponseListUsers>
}