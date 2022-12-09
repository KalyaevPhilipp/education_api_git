package ru.philipp_kalyaev.android.education_api_git

import android.app.Application
import ru.philipp_kalyaev.android.education_api_git.di.AppComponent
import ru.philipp_kalyaev.android.education_api_git.di.AppModule
import ru.philipp_kalyaev.android.education_api_git.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}