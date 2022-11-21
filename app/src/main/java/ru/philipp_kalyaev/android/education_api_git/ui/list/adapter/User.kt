package ru.philipp_kalyaev.android.education_api_git.ui.list.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(
    val userId: String,
    val userName: String,
    val userImage: String,
) : Parcelable
