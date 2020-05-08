package kr.hs.dgsw.avocatalk.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.remote.RetrofitRemote
import kr.hs.dgsw.avocatalk.data.network.service.RegisterService
import kr.hs.dgsw.avocatalk.domain.request.RegisterRequest

class RegisterRemote(override val service:RegisterService) : RetrofitRemote<RegisterService>() {

    fun register(request: RegisterRequest): Single<String>  = service.register(request).map(getResponseMessage())

    fun sendEmail(): Single<String>  = service.sendEmail().map(getResponseMessage())

}