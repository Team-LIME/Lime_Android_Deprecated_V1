package kr.hs.dgsw.avocatalk.domain.request

import kr.hs.dgsw.avocatalk.domain.util.Sha512Converter
import java.security.NoSuchAlgorithmException

class LoginRequest(id: String, pw: String) {
    var id: String? = null
    var pw: String? = null

    init {
        this.id = id
        this.pw = Sha512Converter.ConvertSHA512(pw)
    }
}