package kr.hs.dgsw.avocatalk.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.database.entity.ChatMessageEntity

@Dao
interface ChatMessageDao{

    @Query("SELECT * FROM chat_message_table WHERE chat_room=:chatRoom ORDER BY date DESC")
    fun getMealByChatRoom(chatRoom: Int): Single<List<ChatMessageEntity>>

    @Query("DELETE FROM meal_table")
    fun deleteAll(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChatMessage(entity: List<ChatMessageEntity>): Completable
}