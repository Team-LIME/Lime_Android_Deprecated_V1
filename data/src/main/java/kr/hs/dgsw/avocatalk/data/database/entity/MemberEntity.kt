package kr.hs.dgsw.avocatalk.data.database.entity

import androidx.room.*
import kr.hs.dgsw.avocatalk.data.database.converter.TimestampConverter
import java.util.*

@Entity(tableName = "member_table")
@TypeConverters(TimestampConverter::class)
data class MemberEntity(
    @PrimaryKey
    val idx:Int,
    val email: String,
    val name: String,
    val nickname: String?,
    val intro: String?,
    val profileImage: String?,
    val backgroundImage: String?,
    val isAuth: Boolean,
    @ColumnInfo(name = "date")
    val joinDate: Date
)