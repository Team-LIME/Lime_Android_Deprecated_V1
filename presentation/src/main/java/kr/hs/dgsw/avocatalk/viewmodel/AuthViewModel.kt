package kr.hs.dgsw.avocatalk.viewmodel

import io.reactivex.observers.DisposableCompletableObserver
import kr.hs.dgsw.avocatalk.base.BaseViewModel
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import kr.hs.dgsw.avocatalk.data.widget.SingleLiveEvent
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import kr.hs.dgsw.avocatalk.domain.request.RegisterRequest
import kr.hs.dgsw.avocatalk.domain.usecase.GetTokenUseCase
import kr.hs.dgsw.avocatalk.domain.usecase.LoginUseCase
import kr.hs.dgsw.avocatalk.domain.usecase.RegisterUseCase
import kr.hs.dgsw.avocatalk.domain.usecase.SendEmailUseCase
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private var loginUseCase: LoginUseCase,
    private var registerUseCase: RegisterUseCase,
    private val sendEmailUseCase: SendEmailUseCase
): BaseViewModel() {

    val loginSuccessEvent = SingleLiveEvent<Unit>()
    val registerSuccessEvent = SingleLiveEvent<Unit>()
    val sendEmailSuccessEvent = SingleLiveEvent<Unit>()

    fun sendLoginRequest(loginRequest : LoginRequest){
        addDisposable(loginUseCase.buildUseCaseObservable(LoginUseCase.Params(loginRequest)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    loginSuccessEvent.call()
                    GlobalValue.isLoading.value = false
                }

                override fun onError(e: Throwable) {
                    GlobalValue.onErrorEvent.value = e
                    GlobalValue.isLoading.value = false
                }
            })
    }

    fun sendRegisterRequest(registerRequest: RegisterRequest){
        addDisposable(registerUseCase.buildUseCaseObservable(RegisterUseCase.Params(registerRequest)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    registerSuccessEvent.call()
                    GlobalValue.isLoading.value = false
                }

                override fun onError(e: Throwable) {
                    GlobalValue.onErrorEvent.value = e
                    GlobalValue.isLoading.value = false
                }
            })
    }

    fun sendEmailRequest(){
        addDisposable(sendEmailUseCase.buildUseCaseObservable(),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    sendEmailSuccessEvent.call()
                    GlobalValue.isLoading.value = false
                }

                override fun onError(e: Throwable) {
                    GlobalValue.onErrorEvent.value = e
                    GlobalValue.isLoading.value = false
                }
            })
    }
}
