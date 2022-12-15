package ru.philipp_kalyaev.android.education_api_git.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.philipp_kalyaev.android.education_api_git.data.room.model.UserDb

@Dao
interface UserDao {
    @Query("SELECT * FROM ${UserDb.TABLE_NAME}")
    fun getUsers(): Flow<List<UserDb>>

    @Query("SELECT * FROM ${UserDb.TABLE_NAME} WHERE nodeId=(:userId)")
    suspend fun getUser(userId: Int): List<UserDb>

    @Update(entity = UserDb::class)
    suspend fun updateUser(usedDb: UserDb)

    @Insert(entity = UserDb::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(usedDb: UserDb)

    @Insert(entity = UserDb::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(usedDb: List<UserDb>)

    @Delete(entity = UserDb::class)
    suspend fun deleteUser(usedDb: UserDb)

}