package kr.hs.dgsw.avocatalk.data.network.service

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.network.response.Response
import kr.hs.dgsw.avocatalk.data.network.response.data.LoginData
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import kr.hs.dgsw.avocatalk.domain.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RegisterService {
    @POST("auth/register")
    fun register(@Body request: RegisterRequest): Single<retrofit2.Response<Response<Any>>>

    @GET("auth/email/send")
    fun sendEmail(): Single<retrofit2.Response<Response<Any>>>
}