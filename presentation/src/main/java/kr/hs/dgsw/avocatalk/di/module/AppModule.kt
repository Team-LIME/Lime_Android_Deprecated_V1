package kr.hs.dgsw.avocatalk.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import kr.hs.dgsw.avocatalk.widget.AvocatalkApplication
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun bindContext(application: Application): Context = application

    @Singleton
    @Provides
    fun bindCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun bindAny(): Any = Any()

}