package kr.hs.dgsw.avocatalk.data.network.interceptor

import io.reactivex.disposables.CompositeDisposable
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import kr.hs.dgsw.avocatalk.domain.usecase.LogoutUseCase
import okhttp3.Interceptor
import javax.inject.Inject

class ErrorResponseInterceptor @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val disposable: CompositeDisposable
) : Interceptor {

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
            TOKEN_ERROR -> {
                logOut()
                GlobalValue.logoutEvent.postValue("")
                throw Throwable("Token Expiration")
            }
            else -> return response
        }
    }

    private fun logOut() = 
        disposable.add(logoutUseCase.buildUseCaseObservable().subscribe({ }, { }))
}