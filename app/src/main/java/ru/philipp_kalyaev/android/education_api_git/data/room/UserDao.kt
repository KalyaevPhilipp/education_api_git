package ru.philipp_kalyaev.android.education_api_git.data.room

import androidx.room.*
import ru.philipp_kalyaev.android.education_api_git.data.room.model.UserDb

@Dao
interface UserDao {
    @Query("SELECT * FROM ${UserDb.TABLE_NAME}")
    suspend fun getUsers(): List<UserDb>

    @Query("SELECT * FROM ${UserDb.TABLE_NAME} WHERE nodeId=(:userId)")
    suspend fun getUser(userId: Int): List<UserDb>

    @Update(entity = UserDb::class)
    suspend fun updateUser(usedDb: UserDb)

    @Insert(entity = UserDb::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(usedDb: UserDb)

    @Delete(entity = UserDb::class)
    suspend fun deleteUser(usedDb: UserDb)

}