package kr.hs.dgsw.avocatalk.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.avocatalk.di.scope.PerFragment
import kr.hs.dgsw.avocatalk.view.dialog.CheckPermissionDialog
import kr.hs.dgsw.avocatalk.view.dialog.MessageDialog
import kr.hs.dgsw.avocatalk.view.fragment.main.ChatRoomsListTabFragment
import kr.hs.dgsw.avocatalk.view.fragment.main.FriendsListTabFragment
import kr.hs.dgsw.avocatalk.view.fragment.main.MoreTabFragment

@Module
abstract class FragmentBindingModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingMessageDialog(): MessageDialog

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingCheckPermissionDialog(): CheckPermissionDialog

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingFriendsListTabFragment(): FriendsListTabFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingChatRoomsListTabFragment(): ChatRoomsListTabFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingMoreTabFragment(): MoreTabFragment

}