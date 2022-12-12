package ru.philipp_kalyaev.android.education_api_git.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.philipp_kalyaev.android.education_api_git.ui.details.DetailsFragment
import ru.philipp_kalyaev.android.education_api_git.ui.list.UsersListFragment


object Screens {
    fun Main() = FragmentScreen { UsersListFragment() }
    //fun AddressSearch() = FragmentScreen { AddressSearchFragment() }
    fun Profile(userId: Long) = FragmentScreen("Profile_$userId") { DetailsFragment() }
    //fun Browser(url: String) = ActivityScreen { Intent(Intent.ACTION_VIEW, Uri.parse(url))  }
}