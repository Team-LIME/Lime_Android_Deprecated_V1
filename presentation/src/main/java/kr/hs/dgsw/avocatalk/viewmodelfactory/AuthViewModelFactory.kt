package kr.hs.dgsw.avocatalk.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.avocatalk.domain.usecase.LoginUseCase
import javax.inject.Inject

open class AuthViewModelFactory @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            LoginUseCase::class.java
        ).newInstance(loginUseCase)
}