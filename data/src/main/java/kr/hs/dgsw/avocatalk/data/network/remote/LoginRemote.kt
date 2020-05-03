package kr.hs.dgsw.avocatalk.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.remote.RetrofitRemote
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import kr.hs.dgsw.avocatalk.data.network.response.data.LoginData
import kr.hs.dgsw.avocatalk.data.network.service.LoginService
import org.json.JSONObject

class LoginRemote : RetrofitRemote<LoginService>() {

    override val service: LoginService
        get() = createService(LoginService::class.java)

    fun login(request: LoginRequest): Single<LoginData> {
        return service.login(request).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw Throwable(errorBody.getString("message"))
            }
            return@map it.body()!!.data
        }
    }
}