package kr.hs.dgsw.avocatalk.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.avocatalk.view.activity.LoginActivity
import kr.hs.dgsw.avocatalk.di.scope.PerActivity
import kr.hs.dgsw.avocatalk.view.activity.MainActivity

@Module
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingLoginActivity(): LoginActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingMainActivity(): MainActivity

}