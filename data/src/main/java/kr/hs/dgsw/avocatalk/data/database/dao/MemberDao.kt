package kr.hs.dgsw.avocatalk.data.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.database.entity.MemberEntity

@Dao
interface MemberDao {
    @Query("SELECT * FROM member_table WHERE email=:email")
    fun getMember(email:String): Single<MemberEntity>

    @Query("DELETE FROM member_table")
    fun deleteAllMember(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memberEntity: MemberEntity):Completable
}