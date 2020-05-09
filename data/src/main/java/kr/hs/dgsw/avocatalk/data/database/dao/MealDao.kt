package kr.hs.dgsw.avocatalk.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.database.entity.MealEntity

@Dao
interface MealDao{
    @Query("SELECT * FROM meal_table")
    fun getAllMeal(): Single<List<MealEntity>>

    @Query("SELECT * FROM meal_table WHERE year=:year AND month=:month")
    fun getMealByMonth(year: Int, month: Int): Single<List<MealEntity>>

    @Query("SELECT * FROM meal_table WHERE year=:year AND month=:month AND day=:day")
    fun getMealByDay(year: Int, month: Int, day: Int): Single<MealEntity>

    @Query("DELETE FROM meal_table")
    fun deleteAll(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(entity: List<MealEntity>): Completable
}