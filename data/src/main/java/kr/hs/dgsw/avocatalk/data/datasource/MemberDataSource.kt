package kr.hs.dgsw.avocatalk.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.BaseDataSource
import kr.hs.dgsw.avocatalk.data.database.cache.MemberCache
import kr.hs.dgsw.avocatalk.data.database.entity.MemberEntity
import javax.inject.Inject

class MemberDataSource @Inject constructor(
    override val remote: Any,
    override val cache: MemberCache
) : BaseDataSource<Any, MemberCache>() {

    fun getMember(userEmail: String): Single<MemberEntity> = cache.getMember(userEmail)

    fun insertMember(userEntity: MemberEntity): Completable = cache.insertMember(userEntity)

    fun deleteAllMember(): Completable = cache.deleteAllMember()
}