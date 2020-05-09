package kr.hs.dgsw.avocatalk.domain.request

import kr.hs.dgsw.avocatalk.domain.util.Sha512Converter

class RegisterRequest(email  : String, pw: String, name:String, isConvert: Boolean) {
    var email: String? = null
    var pw: String? = null
    var name: String? = null

    init {
        this.email = email
        this.name = name
        if(isConvert) this.pw = Sha512Converter.ConvertSHA512(pw)
        else this.pw = pw
    }
}