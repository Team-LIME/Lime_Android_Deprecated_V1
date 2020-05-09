package kr.hs.dgsw.avocatalk.data.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.database.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table WHERE email=:email")
    fun getUser(email:String): Single<UserEntity>

    @Query("DELETE FROM user_table")
    fun deleteAllUser(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity):Completable
}