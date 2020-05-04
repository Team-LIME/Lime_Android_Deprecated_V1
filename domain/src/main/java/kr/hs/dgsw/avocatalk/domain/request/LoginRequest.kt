package kr.hs.dgsw.avocatalk.domain.request

import kr.hs.dgsw.avocatalk.domain.util.Sha512Converter

class LoginRequest(email : String, pw: String) {
    var email: String? = null
    var pw: String? = null

    init {
        this.email = email
        this.pw = Sha512Converter.ConvertSHA512(pw)
    }
}