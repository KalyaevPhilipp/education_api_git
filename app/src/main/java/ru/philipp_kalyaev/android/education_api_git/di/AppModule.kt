package ru.philipp_kalyaev.android.education_api_git.di

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.philipp_kalyaev.android.education_api_git.data.api.RetrofitService
import ru.philipp_kalyaev.android.education_api_git.data.room.UserDao
import ru.philipp_kalyaev.android.education_api_git.data.room.UserTypeConverter
import ru.philipp_kalyaev.android.education_api_git.data.room.model.UserDataBase
import ru.philipp_kalyaev.android.education_api_git.data.room.model.UserDb
import javax.inject.Named

@Module
class AppModule(val context: Context) {
    @Provides
    fun getService(retrofit: Retrofit): RetrofitService {
        return retrofit.create<RetrofitService>()
    }

    @Provides
    fun getClient(@Named("base_url") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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
    fun provideDatabase() =
        Room.databaseBuilder(context, UserDataBase::class.java, "EducationDb")
            .allowMainThreadQueries()
            .build()
}

