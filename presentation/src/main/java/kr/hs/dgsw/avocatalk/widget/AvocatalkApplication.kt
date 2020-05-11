package kr.hs.dgsw.avocatalk.widget

import dagger.android.*
import javax.inject.Inject
import kr.hs.dgsw.avocatalk.di.component.DaggerAppComponent

class AvocatalkApplication : DaggerApplication(), HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun androidInjector(): DispatchingAndroidInjector<Any> = activityInjector

    override fun onCreate() {
        super.onCreate()
    }
}