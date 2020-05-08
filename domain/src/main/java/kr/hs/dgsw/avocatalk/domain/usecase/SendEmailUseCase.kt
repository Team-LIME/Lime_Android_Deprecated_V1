package kr.hs.dgsw.avocatalk.domain.usecase

import io.reactivex.Completable
import kr.hs.dgsw.avocatalk.domain.base.BaseUseCase
import kr.hs.dgsw.avocatalk.domain.repository.RegisterRepository
import javax.inject.Inject

class SendEmailUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
): BaseUseCase<Completable>()  {

    override fun buildUseCaseObservable(): Completable {
        return registerRepository.sendEmail()
    }
}
