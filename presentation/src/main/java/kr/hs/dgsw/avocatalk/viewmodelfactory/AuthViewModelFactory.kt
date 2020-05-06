package kr.hs.dgsw.avocatalk.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.avocatalk.domain.usecase.LoginUseCase
import kr.hs.dgsw.avocatalk.domain.usecase.RegisterUseCase
import javax.inject.Inject

open class AuthViewModelFactory @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            LoginUseCase::class.java,
            RegisterUseCase::class.java
        ).newInstance(loginUseCase,registerUseCase)
}