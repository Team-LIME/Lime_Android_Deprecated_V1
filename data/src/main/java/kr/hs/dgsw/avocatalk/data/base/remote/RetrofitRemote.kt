package kr.hs.dgsw.avocatalk.data.base.remote

import kr.hs.dgsw.avocatalk.data.network.interceptor.NoConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

abstract class RetrofitRemote<SV> : BaseRemote<SV>() {

    abstract val noConnectionInterceptor: NoConnectionInterceptor

    protected fun <T> createService(service: Class<T>): T {
        return RETROFIT.create(service)
    }

    abstract val RETROFIT: Retrofit

    abstract val client: OkHttpClient
}