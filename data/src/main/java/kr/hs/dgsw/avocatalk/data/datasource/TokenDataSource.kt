package kr.hs.dgsw.avocatalk.data.datasource

import kr.hs.dgsw.avocatalk.data.base.BaseDataSource
import kr.hs.dgsw.avocatalk.data.database.cache.TokenCache
import kr.hs.dgsw.avocatalk.data.mapper.TokenMapper
import kr.hs.dgsw.avocatalk.domain.model.Token
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    override val remote: Any,
    override val cache: TokenCache
) : BaseDataSource<Any, Any>() {
    fun insertToken(token: Token) = cache.insertToken(TokenMapper.mapToEntity(token))
}