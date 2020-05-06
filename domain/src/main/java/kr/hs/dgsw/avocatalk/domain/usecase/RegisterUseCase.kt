package kr.hs.dgsw.avocatalk.domain.usecase

import io.reactivex.Completable
import kr.hs.dgsw.avocatalk.domain.base.ParamsUseCase
import kr.hs.dgsw.avocatalk.domain.repository.RegisterRepository
import kr.hs.dgsw.avocatalk.domain.request.RegisterRequest
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
): ParamsUseCase<RegisterUseCase.Params, Completable>()  {

    override fun buildUseCaseObservable(params: Params): Completable {
        return registerRepository.register(params.registerRequest)
    }

    data class Params(
        val registerRequest: RegisterRequest
    )
}
