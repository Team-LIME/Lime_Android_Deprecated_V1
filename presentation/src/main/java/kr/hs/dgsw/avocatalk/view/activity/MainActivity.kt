package kr.hs.dgsw.avocatalk.view.activity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kr.hs.dgsw.avocatalk.R


class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }


    private fun initUI(){
        val mainBottomNavigation = main_bottom_navigation
        mainBottomNavigation.itemIconTintList = null

        mainBottomNavigation.menu.getItem(0).setIcon(R.drawable.ic_friends_tab)
        mainBottomNavigation.menu.getItem(1).setIcon(R.drawable.ic_chatting_tab_line)
        mainBottomNavigation.menu.getItem(2).setIcon(R.drawable.ic_more_tab_line)

        NavigationUI.setupWithNavController(mainBottomNavigation, findNavController(R.id.nav_host))

        findNavController(R.id.nav_host).addOnDestinationChangedListener { controller, destination, arguments ->
            mainBottomNavigation.menu.getItem(0).setIcon(R.drawable.ic_friends_tab_line)
            mainBottomNavigation.menu.getItem(1).setIcon(R.drawable.ic_chatting_tab_line)
            mainBottomNavigation.menu.getItem(2).setIcon(R.drawable.ic_more_tab_line)

            when (destination.id) {
                R.id.FriendsListTabFragment ->{
                    mainBottomNavigation.menu.getItem(0).setIcon(R.drawable.ic_friends_tab)
                }
                R.id.ChatRoomsListTabFragment->{
                    mainBottomNavigation.menu.getItem(1).setIcon(R.drawable.ic_chatting_tab)
                }
                R.id.MoreTabFragment ->{
                    mainBottomNavigation.menu.getItem(2).setIcon(R.drawable.ic_more_tab)
                }
            }
        }

    }
}
