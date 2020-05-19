package kr.hs.dgsw.avocatalk.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.avocatalk.data.repository.LogOutRepositoryImpl
import kr.hs.dgsw.avocatalk.data.repository.LoginRepositoryImpl
import kr.hs.dgsw.avocatalk.data.repository.RegisterRepositoryImpl
import kr.hs.dgsw.avocatalk.data.repository.TokenRepositoryImpl
import kr.hs.dgsw.avocatalk.domain.repository.LoginRepository
import kr.hs.dgsw.avocatalk.domain.repository.LogoutRepository
import kr.hs.dgsw.avocatalk.domain.repository.RegisterRepository
import kr.hs.dgsw.avocatalk.domain.repository.TokenRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository = loginRepositoryImpl

    @Singleton
    @Provides
    fun provideRegisterRepository(registerRepositoryImpl: RegisterRepositoryImpl): RegisterRepository = registerRepositoryImpl

    @Singleton
    @Provides
    fun provideTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository = tokenRepositoryImpl

    @Singleton
    @Provides
    fun provideLogOutRepository(logOutRepositoryImpl: LogOutRepositoryImpl): LogoutRepository = logOutRepositoryImpl
}