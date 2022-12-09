package ru.philipp_kalyaev.android.education_api_git.domain.mapper

import android.graphics.Bitmap
import com.squareup.picasso.Picasso
import ru.philipp_kalyaev.android.education_api_git.data.api.model.ResponseListUsers
import ru.philipp_kalyaev.android.education_api_git.data.room.model.UserDb
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User

object UserMapper {
    fun apiToDomain(apiModels: List<ResponseListUsers>): List<User> {
        return apiModels.map {
            User(
                it.id.toString(),
                it.login,
                it.avatarUrl
            )
        }
    }
    fun dbToDomain(dbModels: List<UserDb>):List<User>{
        return dbModels.map{
            User(
                it.id.toString(),
                it.login,
                it.avatarUrl
            )
        }
    }
    fun domainToDb(users:List<User>):List<UserDb>{
        return users.map{
            UserDb(
                it.userId.toInt(),
                0,
                it.userName,
                it.userImage,
                ""
                )
        }
   }
}