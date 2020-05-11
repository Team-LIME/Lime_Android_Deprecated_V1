package kr.hs.dgsw.avocatalk.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.datasource.TokenDataSource
import kr.hs.dgsw.avocatalk.data.mapper.TokenMapper
import kr.hs.dgsw.avocatalk.domain.model.Token
import kr.hs.dgsw.avocatalk.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource
) : TokenRepository {
    override fun getToken(): Single<Token>
            = tokenDataSource.getToken().map { TokenMapper.mapToModel(it) }

    override fun deleteToken(): Completable
            = tokenDataSource.deleteToken()

    override fun checkToken(): Completable
            = tokenDataSource.checkToken().ignoreElement()
}
