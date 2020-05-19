package kr.hs.dgsw.avocatalk.data.database.cache

import android.app.Application
import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.BaseCache
import kr.hs.dgsw.avocatalk.data.database.dao.MemberDao
import kr.hs.dgsw.avocatalk.data.database.entity.MemberEntity
import javax.inject.Inject

class MemberCache @Inject constructor(application: Application) : BaseCache(application) {

    private val memberDao: MemberDao = database.memberDao()

    fun insertMember(memberEntity: MemberEntity): Completable = memberDao.insert(memberEntity)

    fun deleteAllMember(): Completable = memberDao.deleteAllMember()

    fun getMember(userEmail:String): Single<MemberEntity> = memberDao.getMember(userEmail)
}