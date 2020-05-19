package kr.hs.dgsw.avocatalk.view.fragment.main

import kr.hs.dgsw.avocatalk.base.BaseFragment
import kr.hs.dgsw.avocatalk.databinding.FragmentMoreTabBinding
import kr.hs.dgsw.avocatalk.viewmodel.activity.LoginViewModel
import kr.hs.dgsw.avocatalk.viewmodel.fragment.main.MoreTabViewModel
import kr.hs.dgsw.avocatalk.viewmodelfactory.AuthViewModelFactory
import kr.hs.dgsw.avocatalk.viewmodelfactory.fragment.MoreTabViewModelFactory
import javax.inject.Inject

class MoreTabFragment : BaseFragment<FragmentMoreTabBinding,MoreTabViewModel>() {
    @Inject
    lateinit var viewModelFactory: MoreTabViewModelFactory

    override val viewModel: MoreTabViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        super.observerViewModel()
        TODO("Not yet implemented")
    }
}
