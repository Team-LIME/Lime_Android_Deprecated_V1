package kr.hs.dgsw.avocatalk.service

import android.content.Intent
import android.os.IBinder
import dagger.android.DaggerService
import io.reactivex.disposables.CompositeDisposable
import kr.hs.dgsw.avocatalk.domain.repository.TokenRepository
import javax.inject.Inject

class UnCatchTaskService : DaggerService() {

    @Inject
    lateinit var tokenRepository: TokenRepository

    @Inject
    lateinit var disposable: CompositeDisposable

    override fun onBind(intent: Intent): IBinder? = null

    override fun onTaskRemoved(rootIntent: Intent) {
        disposable.add(tokenRepository.deleteToken().subscribe({ }, { }))
        disposable.dispose()
        stopSelf() //서비스도 같이 종료
    }
}
