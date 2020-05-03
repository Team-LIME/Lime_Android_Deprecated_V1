package kr.hs.dgsw.avocatalk.data.repository

import io.reactivex.Completable
import kr.hs.dgsw.avocatalk.data.datasource.LoginDataSource
import kr.hs.dgsw.avocatalk.data.datasource.TokenDataSource
import kr.hs.dgsw.avocatalk.domain.repository.LoginRepository
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import kr.hs.dgsw.avocatalk.domain.model.Token
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
    private val tokenDataSource: TokenDataSource
) : LoginRepository {

    override fun login(loginRequest: LoginRequest): Completable =
        loginDataSource.login(loginRequest).flatMap {
            tokenDataSource.insertToken(Token(it.token)).toSingleDefault(it)
        }.ignoreElement()
}
