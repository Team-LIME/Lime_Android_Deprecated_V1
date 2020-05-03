package kr.hs.dgsw.avocatalk.data.network.response

data class Response<T>(
    val status: Int,
    val message: String,
    val data: T
)