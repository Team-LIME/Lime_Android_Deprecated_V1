package kr.hs.dgsw.avocatalk.data.database.cache

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.BaseCache
import kr.hs.dgsw.avocatalk.data.database.dao.UserDao
import kr.hs.dgsw.avocatalk.data.database.entity.UserEntity
import javax.inject.Inject

class UserCache @Inject constructor(application: Application) : BaseCache(application) {

    private val userDao: UserDao = database.memberDao()

    fun insertUser(userEntity: UserEntity): Completable = userDao.insert(userEntity)

    fun deleteAllUser(): Completable = userDao.deleteAllUser()

    fun getUser(userEmail:String): Single<UserEntity> = userDao.getUser(userEmail)
}