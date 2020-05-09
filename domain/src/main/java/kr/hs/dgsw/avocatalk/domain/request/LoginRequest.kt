package kr.hs.dgsw.avocatalk.domain.request

import kr.hs.dgsw.avocatalk.domain.util.Sha512Converter

class LoginRequest(email : String, pw: String, isConvert:Boolean) {
    var email: String? = null
    var pw: String? = null

    init {
        this.email = email
        if(isConvert) this.pw = Sha512Converter.ConvertSHA512(pw)
        else this.pw = pw
    }
}