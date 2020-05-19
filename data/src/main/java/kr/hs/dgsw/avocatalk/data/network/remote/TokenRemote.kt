package kr.hs.dgsw.avocatalk.data.network.remote

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.remote.RetrofitRemote
import kr.hs.dgsw.avocatalk.data.network.interceptor.NoConnectionInterceptor
import kr.hs.dgsw.avocatalk.data.network.service.TokenService
import kr.hs.dgsw.avocatalk.data.util.Constants
import kr.hs.dgsw.avocatalk.domain.model.Token
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


    class TokenRemote(override val noConnectionInterceptor: NoConnectionInterceptor) : RetrofitRemote<TokenService>() {

    override val service:TokenService
        get() = createService(TokenService::class.java)

    fun checkToken(token: String): Single<String>
            = service.checkToken(token).map(getResponseMessage())

    override val RETROFIT: Retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.DEFAULT_HOST_REST)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .callbackExecutor(Executors.newSingleThreadExecutor())
        .build()

    override val client: OkHttpClient
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okhttpBuilder = OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(noConnectionInterceptor)

            return okhttpBuilder.build()
        }
}