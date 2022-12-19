package ru.philipp_kalyaev.android.education_api_git.data.room

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import ru.philipp_kalyaev.android.education_api_git.data.room.model.UserDb

@Dao
interface UserDao {
    @Query("SELECT * FROM ${UserDb.TABLE_NAME}")
    fun getUsers(): Single<List<UserDb>>

    @Query("SELECT * FROM ${UserDb.TABLE_NAME} WHERE nodeId=(:userId)")
    fun getUser(userId: Int):List<UserDb>

    @Update(entity = UserDb::class)
    fun updateUser(usedDb: UserDb)

    @Insert(entity = UserDb::class, onConflict = OnConflictStrategy.REPLACE)
    fun createUser(usedDb: UserDb)

    @Insert(entity = UserDb::class, onConflict = OnConflictStrategy.REPLACE)
    fun createUser(usedDb: List<UserDb>)

    @Delete(entity = UserDb::class)
    fun deleteUser(usedDb: UserDb)
}