package ru.philipp_kalyaev.android.education_api_git.data.api

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.philipp_kalyaev.android.education_api_git.data.api.model.ResponseListUsers

interface RetrofitService {

    @GET("/users")
    fun getUserList(): Single<List<ResponseListUsers>>

    @GET("/users/{login}/followers")
    fun getSubscribersByUser(@Path("login") username: String): Single<List<ResponseListUsers>>
}