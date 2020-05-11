package kr.hs.dgsw.avocatalk.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.BaseDataSource
import kr.hs.dgsw.avocatalk.data.database.cache.TokenCache
import kr.hs.dgsw.avocatalk.data.database.entity.TokenEntity
import kr.hs.dgsw.avocatalk.data.mapper.TokenMapper
import kr.hs.dgsw.avocatalk.data.network.remote.TokenRemote
import kr.hs.dgsw.avocatalk.domain.model.Token
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    override val remote: TokenRemote,
    override val cache: TokenCache
) : BaseDataSource<TokenRemote, TokenCache>() {
    fun insertToken(token: Token) = cache.insertToken(TokenMapper.mapToEntity(token))
    fun getToken(): Single<TokenEntity> = cache.getToken()
    fun deleteToken(): Completable = cache.deleteToken()
    fun checkToken(): Single<String> = cache.getToken().flatMap { remote.checkToken(it.token) }

}