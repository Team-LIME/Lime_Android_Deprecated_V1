package kr.hs.dgsw.avocatalk.data.network.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.data.base.remote.RetrofitRemote
import kr.hs.dgsw.avocatalk.data.exception.LoginException
import kr.hs.dgsw.avocatalk.data.network.interceptor.ErrorResponseInterceptor
import kr.hs.dgsw.avocatalk.data.network.interceptor.NoConnectionInterceptor
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import kr.hs.dgsw.avocatalk.data.network.response.data.LoginData
import kr.hs.dgsw.avocatalk.data.network.service.LoginService
import kr.hs.dgsw.avocatalk.data.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

class LoginRemote(override val noConnectionInterceptor: NoConnectionInterceptor) : RetrofitRemote<LoginService>() {

    override val service: LoginService
        get() = createService(LoginService::class.java)

    fun login(request: LoginRequest): Single<LoginData> {
        return service.login(request).map {
            if (!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody()!!.string())
                throw LoginException(errorBody.getString("message"))
            }
            return@map it.body()!!.data
        }
    }

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