package ru.philipp_kalyaev.android.education_api_git.di

import dagger.Component
import ru.philipp_kalyaev.android.education_api_git.MainActivity
import ru.philipp_kalyaev.android.education_api_git.ui.details.DetailsViewModel
import ru.philipp_kalyaev.android.education_api_git.ui.list.UserListViewModel
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        NavigationModule::class,
        ContextModule::class,
    ]
)
@Singleton
interface AppComponent {
    fun inject(detailsViewModel: DetailsViewModel)
    fun inject(activity: MainActivity)

    fun provideUserListViewModel(): UserListViewModel
    fun provideDetailsViewModel(): DetailsViewModel.Factory
}