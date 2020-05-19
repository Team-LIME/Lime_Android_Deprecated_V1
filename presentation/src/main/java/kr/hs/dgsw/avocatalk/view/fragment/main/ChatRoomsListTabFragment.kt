package kr.hs.dgsw.avocatalk.view.fragment.main

import kr.hs.dgsw.avocatalk.base.BaseFragment
import kr.hs.dgsw.avocatalk.databinding.FragmentChatRoomsListTabBinding
import kr.hs.dgsw.avocatalk.viewmodel.activity.LoginViewModel
import kr.hs.dgsw.avocatalk.viewmodel.fragment.main.ChatRoomsListTabViewModel
import kr.hs.dgsw.avocatalk.viewmodelfactory.AuthViewModelFactory
import kr.hs.dgsw.avocatalk.viewmodelfactory.fragment.ChatRoomsListTabViewModelFactory
import javax.inject.Inject

class ChatRoomsListTabFragment : BaseFragment<FragmentChatRoomsListTabBinding,ChatRoomsListTabViewModel>() {

    @Inject
    lateinit var viewModelFactory: ChatRoomsListTabViewModelFactory

    override val viewModel: ChatRoomsListTabViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        super.observerViewModel()
        TODO("Not yet implemented")
    }
}
