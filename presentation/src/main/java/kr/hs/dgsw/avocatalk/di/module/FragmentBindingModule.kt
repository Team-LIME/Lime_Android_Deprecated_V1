package kr.hs.dgsw.avocatalk.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.avocatalk.di.scope.PerFragment
import kr.hs.dgsw.avocatalk.view.dialog.MessageDialog

@Module
abstract class FragmentBindingModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingMessageDialog(): MessageDialog

}