package kr.hs.dgsw.avocatalk.data.database.cache

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.BaseCache
import kr.hs.dgsw.personer.project.lime_android.database.dao.TokenDao
import kr.hs.dgsw.personer.project.lime_android.database.entity.TokenEntity
import javax.inject.Inject

class TokenCache @Inject constructor(application: Application) : BaseCache(application) {

    private val tokenDao: TokenDao = database.tokenDao()

    fun insertToken(tokenEntity: TokenEntity): Completable = tokenDao.insert(tokenEntity)

    fun deleteToken(): Completable = tokenDao.deleteToken()

    fun getToken(): Single<TokenEntity> = tokenDao.getToken()
}