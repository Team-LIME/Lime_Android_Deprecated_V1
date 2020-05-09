package kr.hs.dgsw.avocatalk.data.repository

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.datasource.LoginDataSource
import kr.hs.dgsw.avocatalk.data.datasource.TokenDataSource
import kr.hs.dgsw.avocatalk.data.datasource.UserDataSource
import kr.hs.dgsw.avocatalk.data.mapper.UserMapper
import kr.hs.dgsw.avocatalk.domain.repository.LoginRepository
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import kr.hs.dgsw.avocatalk.domain.model.Token
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
    private val tokenDataSource: TokenDataSource,
    private val userDataSource: UserDataSource
) : LoginRepository {

    override fun login(loginRequest: LoginRequest): Single<Boolean> =
        loginDataSource.login(loginRequest)
            .flatMap {
                tokenDataSource.insertToken(Token(it.token)).toSingleDefault(it)
            .flatMap {
                if (!it.user.isAuth) userDataSource.insertUser(UserMapper.mapToEntity(it.user)).toSingleDefault(it.user.isAuth)
                else Single.just(it.user.isAuth)
            }
        }
}
