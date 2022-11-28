package ru.philipp_kalyaev.android.education_api_git.domain.mapper

import ru.philipp_kalyaev.android.education_api_git.data.api.model.ResponseListUsers
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User

object UserMapper {
    fun apiToDomain(apiModels: List<ResponseListUsers>): List<User> {
        return apiModels.map {
            User(
                it.id.toString(),
                it.login,
                it.avatar_url
            )
        }
    }
}