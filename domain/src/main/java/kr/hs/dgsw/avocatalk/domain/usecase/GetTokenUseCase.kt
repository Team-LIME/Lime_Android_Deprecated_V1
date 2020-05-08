package kr.hs.dgsw.avocatalk.domain.usecase

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.domain.base.BaseUseCase
import kr.hs.dgsw.avocatalk.domain.model.Token
import kr.hs.dgsw.avocatalk.domain.repository.TokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
): BaseUseCase<Single<Token>>()  {

    override fun buildUseCaseObservable(): Single<Token> {
        return tokenRepository.getToken()
    }
}
