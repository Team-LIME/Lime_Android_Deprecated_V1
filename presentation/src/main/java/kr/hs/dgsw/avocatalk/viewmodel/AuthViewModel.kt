package kr.hs.dgsw.avocatalk.viewmodel

import io.reactivex.observers.DisposableCompletableObserver
import kr.hs.dgsw.avocatalk.base.BaseViewModel
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue.isLoading
import kr.hs.dgsw.avocatalk.data.widget.SingleLiveEvent
import kr.hs.dgsw.avocatalk.domain.repository.LoginRepository
import kr.hs.dgsw.avocatalk.domain.usecase.LoginUseCase
import kr.hs.dgsw.avocatalk.viewmodelfactory.AuthViewModelFactory
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private var loginUseCase: LoginUseCase
): BaseViewModel() {

    val loginSuccessEvent = SingleLiveEvent<Unit>()

    fun sendLoginRequest(email:String, pw:String){
        addDisposable(loginUseCase.buildUseCaseObservable(LoginUseCase.Params(email, pw)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    loginSuccessEvent.call()
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                    isLoading.value = false
                }
            })
    }
}
