package kr.hs.dgsw.personer.project.lime_android.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.personer.project.lime_android.database.entity.TokenEntity

@Dao
interface TokenDao {
    @Query("SELECT * FROM token_table WHERE idx=0")
    fun getToken(): Single<TokenEntity>


    @Query("DELETE FROM token_table")
    fun deleteToken(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tokenEntity: TokenEntity):Completable
}