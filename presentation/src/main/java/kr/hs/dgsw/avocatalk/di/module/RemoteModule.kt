package kr.hs.dgsw.avocatalk.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kr.hs.dgsw.avocatalk.data.network.interceptor.ErrorResponseInterceptor
import kr.hs.dgsw.avocatalk.data.network.interceptor.NoConnectionInterceptor
import kr.hs.dgsw.avocatalk.data.network.remote.LoginRemote
import kr.hs.dgsw.avocatalk.data.network.remote.RegisterRemote
import kr.hs.dgsw.avocatalk.data.network.remote.TokenRemote
import kr.hs.dgsw.avocatalk.data.network.service.RegisterService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideLoginRemote(noConnectionInterceptor:NoConnectionInterceptor): LoginRemote =
        LoginRemote(noConnectionInterceptor)
    @Provides
    @Singleton
    fun provideRegisterRemote(retrofit: Retrofit): RegisterRemote =
        RegisterRemote(retrofit.create(RegisterService::class.java))

    @Provides
    @Singleton
    fun provideTokenRemote(noConnectionInterceptor: NoConnectionInterceptor): TokenRemote =
        TokenRemote(noConnectionInterceptor)
}