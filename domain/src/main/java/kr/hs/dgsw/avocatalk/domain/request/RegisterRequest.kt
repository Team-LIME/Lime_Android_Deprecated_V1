package kr.hs.dgsw.avocatalk.domain.request

import kr.hs.dgsw.avocatalk.domain.util.Sha512Converter

class RegisterRequest(email  : String, pw: String, name:String) {
    var email: String? = null
    var pw: String? = null
    var name: String? = null

    init {
        this.email = email
        this.pw = Sha512Converter.ConvertSHA512(pw)
        this.name = name
    }
}