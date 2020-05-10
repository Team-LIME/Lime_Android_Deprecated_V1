package kr.hs.dgsw.avocatalk.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.avocatalk.domain.usecase.DeleteTokenUseCase
import kr.hs.dgsw.avocatalk.domain.usecase.LoginUseCase
import kr.hs.dgsw.avocatalk.domain.usecase.RegisterUseCase
import kr.hs.dgsw.avocatalk.domain.usecase.SendEmailUseCase
import javax.inject.Inject

open class AuthViewModelFactory @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val sendEmailUseCase: SendEmailUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            LoginUseCase::class.java,
            RegisterUseCase::class.java,
            SendEmailUseCase::class.java,
            DeleteTokenUseCase::class.java
        ).newInstance(loginUseCase,registerUseCase,sendEmailUseCase,deleteTokenUseCase)
}