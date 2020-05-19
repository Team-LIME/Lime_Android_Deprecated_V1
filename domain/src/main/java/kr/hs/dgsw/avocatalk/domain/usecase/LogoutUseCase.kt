package kr.hs.dgsw.avocatalk.domain.usecase

import io.reactivex.Completable
import kr.hs.dgsw.avocatalk.domain.base.BaseUseCase
import kr.hs.dgsw.avocatalk.domain.repository.LogoutRepository
import kr.hs.dgsw.avocatalk.domain.repository.TokenRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val logoutRepository: LogoutRepository
): BaseUseCase<Completable>()  {
    override fun buildUseCaseObservable(): Completable {
        return logoutRepository.logOut()
    }
}
