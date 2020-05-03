package kr.hs.dgsw.avocatalk.widget

import android.app.Activity
import dagger.android.*
import javax.inject.Inject
import kr.hs.dgsw.avocatalk.di.component.DaggerAppComponent

class AvocatalkApplication : DaggerApplication(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()
    }
}