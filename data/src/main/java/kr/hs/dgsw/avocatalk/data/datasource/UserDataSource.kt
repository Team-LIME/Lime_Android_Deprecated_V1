package kr.hs.dgsw.avocatalk.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.BaseDataSource
import kr.hs.dgsw.avocatalk.data.database.cache.UserCache
import kr.hs.dgsw.avocatalk.data.database.entity.UserEntity
import kr.hs.dgsw.avocatalk.data.network.response.data.LoginData
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import javax.inject.Inject

class UserDataSource @Inject constructor(
    override val remote: Any,
    override val cache: UserCache
) : BaseDataSource<Any, UserCache>() {

    fun getUser(userEmail: String): Single<UserEntity> = cache.getUser(userEmail)

    fun insertUser(userEntity: UserEntity): Completable = cache.insertUser(userEntity)

    fun deleteAllUser(): Completable = cache.deleteAllUser()
}