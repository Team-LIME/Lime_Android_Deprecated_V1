package kr.hs.dgsw.avocatalk.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import kr.hs.dgsw.avocatalk.di.module.*
import kr.hs.dgsw.avocatalk.widget.AvocatalkApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RemoteModule::class,
        RepositoryModule::class,
        NetWorkModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<AvocatalkApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}