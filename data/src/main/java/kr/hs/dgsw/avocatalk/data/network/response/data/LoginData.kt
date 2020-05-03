package kr.hs.dgsw.avocatalk.data.network.response.data

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("x-access-token")
    val token: String
)