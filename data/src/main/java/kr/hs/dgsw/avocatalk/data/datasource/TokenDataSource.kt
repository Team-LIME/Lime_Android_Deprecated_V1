package kr.hs.dgsw.avocatalk.data.datasource

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.BaseDataSource
import kr.hs.dgsw.avocatalk.data.database.cache.TokenCache
import kr.hs.dgsw.avocatalk.data.mapper.TokenMapper
import kr.hs.dgsw.avocatalk.domain.model.Token
import kr.hs.dgsw.personer.project.lime_android.database.entity.TokenEntity
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    override val remote: Any,
    override val cache: TokenCache
) : BaseDataSource<Any, Any>() {
    fun insertToken(token: Token) = cache.insertToken(TokenMapper.mapToEntity(token))
    fun getToken(): Single<TokenEntity> = cache.getToken()
}