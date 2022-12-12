package ru.philipp_kalyaev.android.education_api_git.di

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.Component
import ru.philipp_kalyaev.android.education_api_git.MainActivity
import ru.philipp_kalyaev.android.education_api_git.ui.details.DetailsFragment
import ru.philipp_kalyaev.android.education_api_git.ui.details.DetailsViewModel
import ru.philipp_kalyaev.android.education_api_git.ui.list.UserListViewModel
import ru.philipp_kalyaev.android.education_api_git.ui.list.UsersListFragment

@Component(
    modules = [
        AppModule::class,
        NavigationModule::class,
    ]
)
interface AppComponent {
    fun inject(detailsViewModel: DetailsViewModel)
    fun inject(userListViewModel: UserListViewModel)
    fun inject(activity: MainActivity)
    fun inject(fragment: UsersListFragment)
    fun inject(fragment: DetailsFragment)
}