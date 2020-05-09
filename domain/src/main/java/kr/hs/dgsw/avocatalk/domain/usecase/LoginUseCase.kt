package kr.hs.dgsw.avocatalk.domain.usecase

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.domain.base.ParamsUseCase
import kr.hs.dgsw.avocatalk.domain.repository.LoginRepository
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
): ParamsUseCase<LoginUseCase.Params, Single<Boolean>>()  {

    override fun buildUseCaseObservable(params: Params): Single<Boolean> {
        return loginRepository.login(params.loginRequest)
    }

    data class Params(
        val loginRequest: LoginRequest
    )
}
