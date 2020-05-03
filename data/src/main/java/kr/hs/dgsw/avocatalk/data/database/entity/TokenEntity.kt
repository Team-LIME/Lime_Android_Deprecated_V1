package kr.hs.dgsw.personer.project.lime_android.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token_table")
data class TokenEntity(
    @PrimaryKey
    val idx: Int,
    val token: String){
    constructor(token: String) : this(0, token)
}