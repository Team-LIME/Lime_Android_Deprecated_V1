package kr.hs.dgsw.avocatalk.data.network.response.data

import com.google.gson.annotations.SerializedName
import kr.hs.dgsw.avocatalk.domain.model.User

data class LoginData(
    @SerializedName("x-access-token")
    val token: String,
    val user: User
)