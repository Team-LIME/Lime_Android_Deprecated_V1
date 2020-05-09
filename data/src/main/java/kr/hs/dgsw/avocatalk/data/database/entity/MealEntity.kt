package kr.hs.dgsw.avocatalk.data.database.entity

import androidx.room.Entity
import java.util.*

@Entity(tableName = "meal_table",
    primaryKeys = ["year", "month", "day"])
data class MealEntity(
    val year: Int,
    val month: Int,
    val day: Int,
    val breakfast: String?,
    val lunch: String?,
    val dinner: String?
)