package kr.hs.dgsw.avocatalk.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.avocatalk.data.repository.LoginRepositoryImpl
import kr.hs.dgsw.avocatalk.data.repository.RegisterRepositoryImpl
import kr.hs.dgsw.avocatalk.domain.repository.LoginRepository
import kr.hs.dgsw.avocatalk.domain.repository.RegisterRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository = loginRepositoryImpl

    @Singleton
    @Provides
    fun provideRegisterRepository(registerRepositoryImpl: RegisterRepositoryImpl): RegisterRepository = registerRepositoryImpl
}