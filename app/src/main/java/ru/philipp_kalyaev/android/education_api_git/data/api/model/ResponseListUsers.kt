package ru.philipp_kalyaev.android.education_api_git.data.api.model

import com.google.gson.annotations.SerializedName

data class ResponseListUsers(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("followers_url")
    val followersUrl: String,

)
