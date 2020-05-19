package kr.hs.dgsw.avocatalk.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.avocatalk.base.BaseViewModel
import kr.hs.dgsw.avocatalk.data.util.Constants.SCHOOL_EMAIL_DOMAIN
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import kr.hs.dgsw.avocatalk.domain.usecase.*
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import kr.hs.dgsw.avocatalk.data.widget.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private var loginUseCase: LoginUseCase,
    private val sendEmailUseCase: SendEmailUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase
):BaseViewModel() {

    val email = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    val loginSuccessEvent =
        SingleLiveEvent<Boolean>()
    val sendEmailSuccessEvent =
        SingleLiveEvent<Unit>()
    val sendEmailFailEvent =
        SingleLiveEvent<Unit>()

    val onClickLoginBtnEvent =
        SingleLiveEvent<Unit>()
    val onClickRegisterBtnEvent =
        SingleLiveEvent<Unit>()

    fun onClickLoginBtn(){ onClickLoginBtnEvent.call() }

    fun onClickRegisterBtn(){ onClickRegisterBtnEvent.call() }

    fun sendLoginRequest(){
        val loginRequest = LoginRequest("${email.value}${SCHOOL_EMAIL_DOMAIN}",pw.value!!,true)
        GlobalValue.isLoading.value = true
        addDisposable(loginUseCase.buildUseCaseObservable(LoginUseCase.Params(loginRequest)),
            object : DisposableSingleObserver<Boolean>() {
                override fun onSuccess(t: Boolean) {
                    //isAuth를 Return해줌.
                    //true: 이메일 인증O
                    //false: 이메일 인증X
                    loginSuccessEvent.value = t
                    GlobalValue.isLoading.value = false
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                    GlobalValue.isLoading.value = false
                }

            })
    }

    fun sendEmailRequest(){
        GlobalValue.isLoading.value = true
        addDisposable(sendEmailUseCase.buildUseCaseObservable(),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    sendEmailSuccessEvent.call()
                    GlobalValue.isLoading.value = false
                }

                override fun onError(e: Throwable) {
                    sendEmailFailEvent.call()
                    GlobalValue.isLoading.value = false
                }
            })
    }

    fun deleteToken(){
        addDisposable(deleteTokenUseCase.buildUseCaseObservable(),
            object : DisposableCompletableObserver() {
                override fun onComplete() { }

                override fun onError(e: Throwable) { onErrorEvent.value = e }
            })
    }

}