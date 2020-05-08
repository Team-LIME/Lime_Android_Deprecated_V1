package kr.hs.dgsw.avocatalk.data.network.interceptor

import io.reactivex.disposables.CompositeDisposable
import kr.hs.dgsw.avocatalk.domain.usecase.GetTokenUseCase
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val disposable: CompositeDisposable
) : Interceptor {

    private lateinit var token: String

    override fun intercept(chain: Interceptor.Chain): Response {
        setToken()
        val request =
            if (::token.isInitialized) chain.request().newBuilder().header("x-access-token", token).build()
            else chain.request()

        disposable.dispose()
        return chain.proceed(request)
    }

    private fun setToken() =
        disposable.add(getTokenUseCase.buildUseCaseObservable().subscribe({ token = it.token }, {  }))
}