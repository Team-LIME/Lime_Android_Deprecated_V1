package kr.hs.dgsw.avocatalk.data.datasource

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.BaseDataSource
import kr.hs.dgsw.avocatalk.data.network.remote.LoginRemote
import kr.hs.dgsw.avocatalk.data.network.response.data.LoginData
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    override val remote: LoginRemote,
    override val cache: Any
) : BaseDataSource<LoginRemote, Any>() {

    fun login(loginRequest: LoginRequest): Single<LoginData> = remote.login(loginRequest)
}