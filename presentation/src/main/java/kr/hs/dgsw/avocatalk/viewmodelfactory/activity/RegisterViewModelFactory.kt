package kr.hs.dgsw.avocatalk.viewmodelfactory.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.avocatalk.domain.usecase.*
import javax.inject.Inject

open class RegisterViewModelFactory @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            RegisterUseCase::class.java
        ).newInstance(registerUseCase)
}