package ru.philipp_kalyaev.android.education_api_git.repo
import retrofit2.Call
import retrofit2.http.*
import ru.philipp_kalyaev.android.education_api_git.repo.responseListUsers

interface RetrofitServices{
    @GET
    fun getUserList(): Call<MutableList<responseListUsers>>
}