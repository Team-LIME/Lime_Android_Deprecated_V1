package kr.hs.dgsw.avocatalk.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.avocatalk.data.network.remote.LoginRemote
import kr.hs.dgsw.avocatalk.data.network.remote.RegisterRemote
import kr.hs.dgsw.avocatalk.data.network.service.RegisterService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideLoginRemote(): LoginRemote = LoginRemote()

    @Singleton
    @Provides
    fun provideRegisterRemote(retrofit: Retrofit): RegisterRemote =
        RegisterRemote(retrofit.create(RegisterService::class.java))
}