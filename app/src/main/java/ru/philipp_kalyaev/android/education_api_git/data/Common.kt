package ru.philipp_kalyaev.android.education_api_git.data

import ru.philipp_kalyaev.android.education_api_git.data.api.RetrofitClient
import ru.philipp_kalyaev.android.education_api_git.data.api.RetrofitService

// todo На первое время действительно можно воспользоваться объектом,
//  потом перенесём это в Dagger или Koin
object Common {

    private const val BASE_URL = "https://api.github.com"

    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}