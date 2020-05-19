package kr.hs.dgsw.avocatalk.viewmodel.activity

import com.bumptech.glide.load.HttpException
import io.reactivex.observers.DisposableCompletableObserver
import kr.hs.dgsw.avocatalk.base.BaseViewModel
import kr.hs.dgsw.avocatalk.data.exception.TokenException
import kr.hs.dgsw.avocatalk.domain.usecase.CheckTokenUseCase
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import kr.hs.dgsw.avocatalk.data.widget.SingleLiveEvent
import java.net.UnknownHostException
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val checkTokenUseCase: CheckTokenUseCase
): BaseViewModel() {

    val successTokenCheck =
        SingleLiveEvent<Unit>()

    fun checkToken() {
        addDisposable(checkTokenUseCase.buildUseCaseObservable(),object : DisposableCompletableObserver() {
            override fun onComplete() {
                onSuccess()
            }

            override fun onError(e: Throwable) {
                GlobalValue.isLoading.value = false
                when (e) {
                    is TokenException ->
                        onFail()
                    is HttpException ->
                        when(e.statusCode) {
                            200 -> onSuccess()
                            else -> onFail()
                    }
                    is UnknownHostException -> onSuccess()
                }
            }

        })
    }

    fun onSuccess(){
        successTokenCheck.call()
    }

    fun onFail(){
        onErrorEvent.value = TokenException("")
    }
}