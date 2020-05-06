package kr.hs.dgsw.avocatalk.data.repository

import io.reactivex.Completable
import kr.hs.dgsw.avocatalk.data.datasource.RegisterDataSource
import kr.hs.dgsw.avocatalk.domain.repository.RegisterRepository
import kr.hs.dgsw.avocatalk.domain.request.RegisterRequest
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerDataSource: RegisterDataSource
) : RegisterRepository {
    override fun register(registerRequest: RegisterRequest): Completable {
        return registerDataSource.register(registerRequest).ignoreElement()
    }
}
