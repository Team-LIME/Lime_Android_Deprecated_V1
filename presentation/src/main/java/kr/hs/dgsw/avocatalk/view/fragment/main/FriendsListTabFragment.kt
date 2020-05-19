package kr.hs.dgsw.avocatalk.view.fragment.main

import kr.hs.dgsw.avocatalk.base.BaseFragment
import kr.hs.dgsw.avocatalk.databinding.FragmentFriendsListTabBinding
import kr.hs.dgsw.avocatalk.viewmodel.activity.LoginViewModel
import kr.hs.dgsw.avocatalk.viewmodel.fragment.main.FriendsListTabViewModel
import kr.hs.dgsw.avocatalk.viewmodelfactory.AuthViewModelFactory
import kr.hs.dgsw.avocatalk.viewmodelfactory.fragment.FriendsListTabViewModelFactory
import javax.inject.Inject

class FriendsListTabFragment : BaseFragment<FragmentFriendsListTabBinding,FriendsListTabViewModel>() {
    @Inject
    lateinit var mLoginViewModelFactory: FriendsListTabViewModelFactory

    override val viewModel: FriendsListTabViewModel
        get() = getViewModel(mLoginViewModelFactory)

    override fun observerViewModel() {
        super.observerViewModel()
        TODO("Not yet implemented")
    }
}
