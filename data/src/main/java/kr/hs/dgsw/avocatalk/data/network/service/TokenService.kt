package kr.hs.dgsw.avocatalk.data.network.service

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.network.response.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface TokenService {
    @POST("auth/login")
    fun checkToken(@Header("x-access-token") token:String): Single<retrofit2.Response<Response<Any>>>
}