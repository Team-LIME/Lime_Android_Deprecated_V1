package kr.hs.dgsw.avocatalk.data.base.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import kr.hs.dgsw.avocatalk.data.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

abstract class RetrofitRemote<SV> : BaseRemote<SV>() {

    protected fun <T> createService(service: Class<T>): T {
        return RETROFIT.create(service)
    }

    private val RETROFIT: Retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.DEFAULT_HOST)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .callbackExecutor(Executors.newSingleThreadExecutor())
        .build()

    private val client: OkHttpClient
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okhttpBuilder = OkHttpClient().newBuilder().addInterceptor(interceptor)
            return okhttpBuilder.build()
        }

}