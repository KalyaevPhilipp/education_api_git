package ru.philipp_kalyaev.android.education_api_git.repo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    private var retrofit: Retrofit?=null
    fun getClient(baseUrl: String):Retrofit{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}