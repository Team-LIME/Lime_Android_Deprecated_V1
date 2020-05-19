package kr.hs.dgsw.avocatalk.viewmodel.activity

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableCompletableObserver
import kr.hs.dgsw.avocatalk.R
import kr.hs.dgsw.avocatalk.base.BaseViewModel
import kr.hs.dgsw.avocatalk.data.util.Constants.SCHOOL_EMAIL_DOMAIN
import kr.hs.dgsw.avocatalk.domain.request.RegisterRequest
import kr.hs.dgsw.avocatalk.domain.usecase.RegisterUseCase
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import kr.hs.dgsw.avocatalk.data.widget.SingleLiveEvent
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private var registerUseCase: RegisterUseCase
): BaseViewModel() {

    val onClickShowTerms1Event = SingleLiveEvent<Unit>()
    val onClickShowTerms2Event = SingleLiveEvent<Unit>()
    val onClickRegisterBtnEvent = SingleLiveEvent<Unit>()
    val registerSuccessEvent = SingleLiveEvent<Unit>()

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val checkPw = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun onClickShowTerms1(){ onClickShowTerms1Event.call() }
    fun onClickShowTerms2(){ onClickShowTerms2Event.call() }
    fun onClickRegisterBtn(){ onClickRegisterBtnEvent.call() }

    fun sendRegisterRequest(){
        val registerRequest = RegisterRequest("${email.value}${SCHOOL_EMAIL_DOMAIN}",pw.value!!,name.value!!,true)

        addDisposable(registerUseCase.buildUseCaseObservable(RegisterUseCase.Params(registerRequest)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    registerSuccessEvent.call()
                    GlobalValue.isLoading.value = false
                }

                override fun onError(e: Throwable) {
                    onErrorEvent.value = e
                    GlobalValue.isLoading.value = false
                }
            })
    }
}