package kr.hs.dgsw.avocatalk.domain.repository

import io.reactivex.Single
import kr.hs.dgsw.avocatalk.domain.model.Token

interface TokenRepository {
    fun getToken(): Single<Token>
}