package ru.philipp_kalyaev.android.education_api_git.di

import dagger.Component
import ru.philipp_kalyaev.android.education_api_git.ui.details.DetailsViewModel
import ru.philipp_kalyaev.android.education_api_git.ui.list.UserListViewModel

@Component(
    modules = [
        AppModule::class,
    ]
)
interface AppComponent {
    fun inject(detailsViewModel: DetailsViewModel)
    fun inject(userListViewModel: UserListViewModel)
}