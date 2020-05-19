package kr.hs.dgsw.avocatalk.data.network.interceptor

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kr.hs.dgsw.avocatalk.data.exception.NoConnectivityException
import kr.hs.dgsw.avocatalk.data.exception.NoInternetException
import kr.hs.dgsw.avocatalk.data.util.Constants
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import okhttp3.Interceptor
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import javax.inject.Inject

@SuppressLint("MissingPermission")
class NoConnectionInterceptor @Inject constructor(
  private val context: Context
) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
    return if (!isConnectionOn()) {
      val exception = NoConnectivityException("인터넷 연결이 끊겼습니다. WI-FI나 데이터 연결을 확인해주세요")
      GlobalValue.disconnectInternetEvent.postValue(exception)
      throw exception
//    } else if (!isInternetAvailable()) {
//      val exception = NoInternetException("서버와의 연결이 끊겼습니다. WI-FI나 데이터 연결을 확인해주세요")
//      GlobalValue.disconnectInternetEvent.postValue(exception)
//      throw exception
    } else {
      chain.proceed(chain.request())
    }
  }

  private fun preAndroidMInternetCheck(connectivityManager: ConnectivityManager): Boolean {
    val activeNetwork = connectivityManager.activeNetworkInfo

    if (activeNetwork != null) {
      return (activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
              activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
    }
    return false
  }

  private fun isInternetAvailable(): Boolean {
    return try {
      val timeoutMs = 15000
      val sock = Socket()
      val sockaddr = InetSocketAddress(Constants.DEFAULT_HOST, 404)

      sock.connect(sockaddr, timeoutMs)
      sock.close()

      true
    } catch (e: IOException) {
      println("")
      false
    }

  }

  private fun postAndroidMInternetCheck(connectivityManager: ConnectivityManager): Boolean {
    val network = connectivityManager.activeNetwork
    val connection = connectivityManager.getNetworkCapabilities(network)

    return connection != null && (
            connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
  }

  private fun isConnectionOn(): Boolean {
    val connectivityManager = context.getSystemService(
      Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    return if (android.os.Build.VERSION.SDK_INT >=
        android.os.Build.VERSION_CODES.M) {
        postAndroidMInternetCheck(connectivityManager)
    } else {
        preAndroidMInternetCheck(connectivityManager)
    }
  }
}