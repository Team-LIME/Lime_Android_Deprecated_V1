package kr.hs.dgsw.avocatalk.data.network.service

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.network.response.Response
import kr.hs.dgsw.avocatalk.data.network.response.data.LoginData
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/v2/auth/login")
    fun login(@Body request: LoginRequest): Single<retrofit2.Response<Response<LoginData>>>
}