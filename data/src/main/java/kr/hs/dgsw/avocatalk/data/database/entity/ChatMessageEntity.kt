package kr.hs.dgsw.avocatalk.data.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kr.hs.dgsw.avocatalk.data.database.converter.TimestampConverter
import java.util.*

@Entity(tableName = "chat_message_table")
@TypeConverters(TimestampConverter::class)
data class ChatMessageEntity(
    @PrimaryKey
    val id:String,
    val chat_room: Int,
    val message: String,
    val userId: String,
    val userName: String,
    val userProfileImage: String,

    @NonNull
    @ColumnInfo(name = "date")
    val date: Date,
    val type: Int
)