package ru.philipp_kalyaev.android.education_api_git.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.philipp_kalyaev.android.education_api_git.data.api.RetrofitService
import ru.philipp_kalyaev.android.education_api_git.data.room.model.UserDataBase
import ru.philipp_kalyaev.android.education_api_git.domain.GithubRepositoryImpl
import ru.philipp_kalyaev.android.feature_repo.GithubRepository
import javax.inject.Named

@Module
abstract class AppModule {

    @Binds
    abstract fun githubRepository(impl: GithubRepositoryImpl): GithubRepository

    companion object {
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

        @Provides
        fun provideDao(userDatabase: UserDataBase) = userDatabase.userDataBaseDao()

        @Provides
        fun provideDatabase(context: Context) =
            Room.databaseBuilder(context, UserDataBase::class.java, "EducationDb").build()
    }
}

