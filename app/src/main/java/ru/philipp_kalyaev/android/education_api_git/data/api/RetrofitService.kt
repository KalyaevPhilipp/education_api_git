package ru.philipp_kalyaev.android.education_api_git.data.api

import retrofit2.Call
import retrofit2.http.GET
import ru.philipp_kalyaev.android.education_api_git.data.api.model.ResponseListUsers

interface RetrofitService {

    @GET("users")
    fun getUserList(): Call<List<ResponseListUsers>>
}