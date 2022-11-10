package ru.philipp_kalyaev.android.education_api_git.repo

object common {
    private val BASE_URL = "https://api.github.com/users"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}