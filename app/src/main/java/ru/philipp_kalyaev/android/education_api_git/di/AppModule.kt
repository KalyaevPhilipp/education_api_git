package ru.philipp_kalyaev.android.education_api_git.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.philipp_kalyaev.android.education_api_git.data.api.RetrofitService
import javax.inject.Named

@Module
class AppModule {

    @Provides
    fun getService(retrofit: Retrofit): RetrofitService {
        return retrofit.create<RetrofitService>()
    }

    @Provides
    fun getClient(@Named("base_url") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named("base_url")
    fun baseUrl(): String {
        return "https://api.github.com"
    }
}