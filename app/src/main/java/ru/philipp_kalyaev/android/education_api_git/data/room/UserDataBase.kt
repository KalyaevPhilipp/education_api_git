package ru.philipp_kalyaev.android.education_api_git.data.room.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.philipp_kalyaev.android.education_api_git.data.room.UserDao
import ru.philipp_kalyaev.android.education_api_git.data.room.UserTypeConverter

@Database(entities = [UserDb::class],version = 1, exportSchema = true)
@TypeConverters(UserTypeConverter::class)
abstract class UserDataBase:RoomDatabase(){
    abstract fun userDataBaseDao():UserDao
}
