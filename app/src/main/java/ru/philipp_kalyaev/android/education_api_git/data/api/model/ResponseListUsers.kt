package ru.philipp_kalyaev.android.education_api_git.data.api.model

// todo В котлиновском коде не используем нижнее подчеркивание в названии переменных.
//  Обойти это можно либо через настройку Gson, либо через аннотацию @SerializedName,
//  на уроке рассмотрим

// todo А ещё можно не объявлять все переменные из ответа сервера, а использовать только те,
//  которыми мы будем пользоваться

data class ResponseListUsers(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean,
)
