package ru.philipp_kalyaev.android.education_api_git.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.philipp_kalyaev.android.education_api_git.data.room.model.UserDb.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserDb(
    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "nodeId")
    val nodeId: Int,
    @ColumnInfo(name = "login")
    val login: String,
    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String,
    @ColumnInfo(name = "followersUrl")
    val followersUrl: String,
    ){
    companion object{
        const val TABLE_NAME = "UserDataBase"
    }
}