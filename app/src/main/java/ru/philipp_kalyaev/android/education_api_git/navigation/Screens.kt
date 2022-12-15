package ru.philipp_kalyaev.android.education_api_git.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.philipp_kalyaev.android.education_api_git.ui.details.DetailsFragment
import ru.philipp_kalyaev.android.education_api_git.ui.list.UsersListFragment
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User


object Screens {
    fun Main() = FragmentScreen { UsersListFragment() }

    fun Details(user: User) = FragmentScreen { DetailsFragment.newInstance(user) }
}