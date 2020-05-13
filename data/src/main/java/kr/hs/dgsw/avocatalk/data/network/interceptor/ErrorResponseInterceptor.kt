package kr.hs.dgsw.avocatalk.data.network.interceptor

import kr.hs.dgsw.avocatalk.data.exception.TokenException
import okhttp3.Interceptor

class ErrorResponseInterceptor : Interceptor {

    private val TIME_OUT_ERROR = 408
    private val NOT_FOUND_ERROR = 404
    private val SERVER_ERROR = 500
    private val TOKEN_ERROR = 410

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

        val request = chain.request()
        val response = chain.proceed(request)

        when (response.code) {
            TIME_OUT_ERROR -> throw Throwable("Time Out")
            NOT_FOUND_ERROR, SERVER_ERROR -> throw  Throwable("unknown Error")
            TOKEN_ERROR -> throw TokenException("Token Expiration")
            else -> return response
        }
    }
}