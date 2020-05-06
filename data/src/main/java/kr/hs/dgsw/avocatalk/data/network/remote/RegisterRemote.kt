package kr.hs.dgsw.avocatalk.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.remote.RetrofitRemote
import kr.hs.dgsw.avocatalk.data.network.service.RegisterService
import kr.hs.dgsw.avocatalk.domain.request.RegisterRequest

class RegisterRemote : RetrofitRemote<RegisterService>() {

    override val service: RegisterService
        get() = createService(RegisterService::class.java)

    fun register(request: RegisterRequest): Single<String>  = service.register(request).map(getResponseMessage())

}