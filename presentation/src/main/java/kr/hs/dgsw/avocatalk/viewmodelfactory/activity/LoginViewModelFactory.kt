package kr.hs.dgsw.avocatalk.viewmodelfactory.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.avocatalk.domain.usecase.*
import javax.inject.Inject

open class LoginViewModelFactory @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sendEmailUseCase: SendEmailUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            LoginUseCase::class.java,
            SendEmailUseCase::class.java,
            DeleteTokenUseCase::class.java
        ).newInstance(loginUseCase,sendEmailUseCase,deleteTokenUseCase)
}