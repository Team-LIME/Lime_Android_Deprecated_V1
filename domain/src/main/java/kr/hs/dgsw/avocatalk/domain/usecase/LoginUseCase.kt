package kr.hs.dgsw.avocatalk.domain.usecase

import io.reactivex.Completable
import kr.hs.dgsw.avocatalk.domain.base.ParamsUseCase
import kr.hs.dgsw.avocatalk.domain.repository.LoginRepository
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
): ParamsUseCase<LoginUseCase.Params, Completable>()  {

    override fun buildUseCaseObservable(params: Params): Completable {
        return loginRepository.login(LoginRequest(params.id, params.pw))
    }

    data class Params(
        val id: String,
        val pw: String
    )
}
