package kr.hs.dgsw.avocatalk.data.base.remote

import io.reactivex.functions.Function
import kr.hs.dgsw.avocatalk.data.network.response.Response
import org.json.JSONObject
import retrofit2.HttpException

abstract class BaseRemote<SV> {
    abstract val service: SV

    protected fun <T> getResponseData(): Function<retrofit2.Response<Response<T>>, T> {
        return Function { response: retrofit2.Response<Response<T>> ->
            checkError(response)
            response.body()!!.data
        }
    }

    protected fun getResponseMessage(): Function<retrofit2.Response<Response<Any>>, String> {
        return Function { response: retrofit2.Response<Response<Any>> ->
            checkError(response)
            response.body()!!.message
        }
    }

    private fun checkError(response: retrofit2.Response<*>) {
        if (!response.isSuccessful) {
            throw HttpException(response)
        }
    }
}