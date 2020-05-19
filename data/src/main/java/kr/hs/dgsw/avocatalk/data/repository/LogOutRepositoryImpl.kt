package kr.hs.dgsw.avocatalk.data.repository

import io.reactivex.Completable
import kr.hs.dgsw.avocatalk.data.datasource.TokenDataSource
import kr.hs.dgsw.avocatalk.data.datasource.UserDataSource
import kr.hs.dgsw.avocatalk.domain.repository.LogoutRepository
import javax.inject.Inject

class LogOutRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource,
    private val userDataSource: UserDataSource
) : LogoutRepository {
    override fun logOut(): Completable =
        tokenDataSource.deleteToken().andThen { userDataSource.deleteUser() }
}
