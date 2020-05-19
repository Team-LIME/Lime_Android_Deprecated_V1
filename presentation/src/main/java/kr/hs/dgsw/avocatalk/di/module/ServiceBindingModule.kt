package kr.hs.dgsw.avocatalk.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.avocatalk.di.scope.PerService
import kr.hs.dgsw.avocatalk.service.UnCatchTaskService

@Module
abstract class ServiceBindingModule {

    @PerService
    @ContributesAndroidInjector
    abstract fun bindingUnCatchTaskService() : UnCatchTaskService
}