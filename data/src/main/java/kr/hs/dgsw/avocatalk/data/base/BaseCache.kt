package kr.hs.dgsw.avocatalk.data.base

import android.app.Application
import kr.hs.dgsw.avocatalk.data.database.RoomDatabase

open class BaseCache(application: Application) {
    protected val database = RoomDatabase.getInstance(application)!!
}