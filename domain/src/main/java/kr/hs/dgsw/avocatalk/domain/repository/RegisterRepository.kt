package kr.hs.dgsw.avocatalk.domain.repository

import io.reactivex.Completable
import kr.hs.dgsw.avocatalk.domain.request.RegisterRequest

interface RegisterRepository {
    fun register(registerRequest: RegisterRequest): Completable

    fun sendEmail(): Completable
}