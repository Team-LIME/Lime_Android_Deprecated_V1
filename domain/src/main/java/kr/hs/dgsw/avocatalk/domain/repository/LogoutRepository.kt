package kr.hs.dgsw.avocatalk.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest

interface LogoutRepository {
    fun logOut(): Completable
}