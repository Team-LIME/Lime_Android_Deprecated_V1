package kr.hs.dgsw.avocatalk.viewmodelfactory.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.avocatalk.domain.usecase.*
import javax.inject.Inject

open class MoreTabViewModelFactory @Inject constructor(


) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(


        ).newInstance()
}