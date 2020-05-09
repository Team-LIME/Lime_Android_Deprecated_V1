package kr.hs.dgsw.avocatalk.data.repository

import io.reactivex.Completable
import kr.hs.dgsw.avocatalk.data.datasource.LoginDataSource
import kr.hs.dgsw.avocatalk.data.datasource.RegisterDataSource
import kr.hs.dgsw.avocatalk.data.datasource.TokenDataSource
import kr.hs.dgsw.avocatalk.domain.model.Token
import kr.hs.dgsw.avocatalk.domain.repository.LoginRepository
import kr.hs.dgsw.avocatalk.domain.repository.RegisterRepository
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import kr.hs.dgsw.avocatalk.domain.request.RegisterRequest
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerDataSource: RegisterDataSource,
    private val loginDataSource: LoginDataSource,
    private val tokenDataSource: TokenDataSource,
    private val loginRepository: LoginRepository
) : RegisterRepository {
//    override fun register(registerRequest: RegisterRequest): Completable =
//        registerDataSource.register(registerRequest).flatMap {
//            loginRepository.login(LoginRequest(registerRequest.email!!, registerRequest.pw!!)).andThen(
//                registerDataSource.sendEmail().ignoreElement()
//            ).toSingleDefault(it)
//        }.ignoreElement()

    override fun register(registerRequest: RegisterRequest): Completable =
        registerDataSource.register(registerRequest).flatMap {
            loginDataSource.login(LoginRequest(registerRequest.email!!, registerRequest.pw!!,false)).flatMap {
                    tokenDataSource.insertToken(Token(it.token)).toSingleDefault(it) }.ignoreElement().andThen(
                registerDataSource.sendEmail().ignoreElement()
            ).toSingleDefault(it)
        }.ignoreElement()

    override fun sendEmail(): Completable
            = registerDataSource.sendEmail().ignoreElement()
}
