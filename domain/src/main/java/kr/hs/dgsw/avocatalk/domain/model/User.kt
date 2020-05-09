package kr.hs.dgsw.avocatalk.domain.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
    val idx:Int,
    val email: String,
    val name: String,
    val nickname: String?,
    val intro: String?,
    @SerializedName("profile_image")
    val profileImage: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("is_auth")
    val isAuth: Boolean,
    @SerializedName("join_date")
    val joinDate: Date
)