package kr.hs.dgsw.avocatalk.domain.repository

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest

interface LoginRepository {
    fun login(loginRequest: LoginRequest): Single<Boolean>
}