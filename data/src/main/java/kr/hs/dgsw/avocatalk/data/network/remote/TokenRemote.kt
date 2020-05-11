package kr.hs.dgsw.avocatalk.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.remote.RetrofitRemote
import kr.hs.dgsw.avocatalk.data.network.service.TokenService
import kr.hs.dgsw.avocatalk.domain.model.Token

class TokenRemote: RetrofitRemote<TokenService>() {

    override val service:TokenService
        get() = createService(TokenService::class.java)

    fun checkToken(token: String): Single<String>
            = service.checkToken(token).map(getResponseMessage())
}