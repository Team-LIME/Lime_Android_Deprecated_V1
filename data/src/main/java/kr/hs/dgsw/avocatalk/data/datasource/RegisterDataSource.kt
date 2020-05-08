package kr.hs.dgsw.avocatalk.data.datasource

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.BaseDataSource
import kr.hs.dgsw.avocatalk.data.network.remote.RegisterRemote
import kr.hs.dgsw.avocatalk.domain.request.RegisterRequest
import javax.inject.Inject

class RegisterDataSource @Inject constructor(
    override val remote: RegisterRemote,
    override val cache: Any
) : BaseDataSource<RegisterRemote, Any>() {

    fun register(registerRequest: RegisterRequest): Single<String> = remote.register(registerRequest)

    fun sendEmail(): Single<String> = remote.sendEmail()
}