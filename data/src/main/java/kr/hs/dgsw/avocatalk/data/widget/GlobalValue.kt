package kr.hs.dgsw.avocatalk.data.widget

object GlobalValue {
    val onErrorEvent: SingleLiveEvent<Throwable> = SingleLiveEvent()
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
}