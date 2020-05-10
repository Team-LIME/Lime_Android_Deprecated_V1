package kr.hs.dgsw.avocatalk.data.widget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object GlobalValue {
    val onErrorEvent: SingleLiveEvent<Throwable> = SingleLiveEvent()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }
}