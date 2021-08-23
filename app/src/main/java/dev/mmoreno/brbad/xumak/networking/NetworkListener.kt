package dev.mmoreno.brbad.xumak.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData

class NetworkListener : ConnectivityManager.NetworkCallback() {

  private val isNetworkAvailable = MutableLiveData(false)

  fun checkNetworkAvailability(context: Context): MutableLiveData<Boolean> {

    val connectivityManager =
      context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // Check if version code is greater than API 24
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
      connectivityManager.registerDefaultNetworkCallback(this)
    } else {
      val builder: NetworkRequest.Builder = NetworkRequest.Builder()
      connectivityManager.registerNetworkCallback(
        builder.build(), this
      )
    }
    var isConnected = false
    connectivityManager.allNetworks.forEach { network ->
      val networkCapability = connectivityManager.getNetworkCapabilities(network)
      networkCapability?.let {
        if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
          isConnected = true
          return@forEach
        }
      }
    }
    isNetworkAvailable.value = isConnected
    return isNetworkAvailable
  }

  override fun onAvailable(network: Network) {
    isNetworkAvailable.postValue(true)
  }

  override fun onLost(network: Network) {
    isNetworkAvailable.postValue(false)
  }
}