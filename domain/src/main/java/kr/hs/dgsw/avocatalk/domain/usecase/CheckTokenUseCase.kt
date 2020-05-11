package kr.hs.dgsw.avocatalk.domain.usecase

import io.reactivex.Completable
import kr.hs.dgsw.avocatalk.domain.base.BaseUseCase
import kr.hs.dgsw.avocatalk.domain.repository.TokenRepository
import javax.inject.Inject

class CheckTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
): BaseUseCase<Completable>()  {
    override fun buildUseCaseObservable(): Completable {
        return tokenRepository.checkToken()
    }
}
