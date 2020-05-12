package kr.hs.dgsw.avocatalk.view.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kr.hs.dgsw.avocatalk.R
import kr.hs.dgsw.avocatalk.base.BaseFragment
import kr.hs.dgsw.avocatalk.databinding.FragmentChatRoomsListTabBinding

/**
 * A simple [Fragment] subclass.
 */
class ChatRoomsListTabFragment : BaseFragment<FragmentChatRoomsListTabBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_rooms_list_tab, container, false)
    }

}
