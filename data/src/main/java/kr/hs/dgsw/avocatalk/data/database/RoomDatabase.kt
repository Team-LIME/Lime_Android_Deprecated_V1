package kr.hs.dgsw.avocatalk.data.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import kr.hs.dgsw.personer.project.lime_android.database.dao.TokenDao
import kr.hs.dgsw.personer.project.lime_android.database.entity.TokenEntity

@Database(entities = [TokenEntity::class],
    version = 1, exportSchema = false
)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun tokenDao(): TokenDao

    companion object {

        private var instance: RoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        RoomDatabase::class.java, "avokatalk_DataBase")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            return instance
        }
    }
}
