package kr.hs.dgsw.avocatalk.domain.util

import java.math.BigInteger
import java.security.MessageDigest

object Sha512Converter {
    fun ConvertSHA512(input: String): String? {
        return if(input.isNullOrBlank()){
            null
        }else{
            var toReturn: String? = null
            try {
                val digest =
                    MessageDigest.getInstance("SHA-512")
                digest.reset()
                digest.update(input.toByteArray(charset("utf8")))
                toReturn = String.format("%0128x", BigInteger(1, digest.digest()))
            } catch (e: Exception) {
                e.printStackTrace()
            }
            toReturn
        }
    }
}
