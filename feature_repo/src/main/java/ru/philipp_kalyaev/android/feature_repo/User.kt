package ru.philipp_kalyaev.android.feature_repo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(
    val userId: String,
    val userName: String,
    val userImage: String,
) : Parcelable
