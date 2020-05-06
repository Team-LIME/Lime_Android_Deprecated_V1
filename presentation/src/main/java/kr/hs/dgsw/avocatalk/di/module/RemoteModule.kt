package kr.hs.dgsw.avocatalk.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.avocatalk.data.network.remote.LoginRemote
import kr.hs.dgsw.avocatalk.data.network.remote.RegisterRemote
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideLoginRemote(): LoginRemote = LoginRemote()

    @Singleton
    @Provides
    fun provideRegisterRemote(): RegisterRemote = RegisterRemote()
}