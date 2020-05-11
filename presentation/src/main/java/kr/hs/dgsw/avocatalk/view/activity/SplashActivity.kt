package kr.hs.dgsw.avocatalk.view.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.EmptyResultSetException
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.observers.DisposableCompletableObserver
import kr.hs.dgsw.avocatalk.BR
import kr.hs.dgsw.avocatalk.R
import kr.hs.dgsw.avocatalk.base.BaseActivity
import kr.hs.dgsw.avocatalk.data.exception.TokenException
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue.isLoading
import kr.hs.dgsw.avocatalk.databinding.ActivitySplashBinding
import kr.hs.dgsw.avocatalk.eventobserver.dialog.CheckPermissionEventObserver
import kr.hs.dgsw.avocatalk.eventobserver.dialog.MessageEventObserver
import kr.hs.dgsw.avocatalk.view.dialog.CheckPermissionDialog
import kr.hs.dgsw.avocatalk.view.dialog.MessageDialog
import kr.hs.dgsw.avocatalk.viewmodel.AuthViewModel
import kr.hs.dgsw.avocatalk.viewmodelfactory.AuthViewModelFactory
import retrofit2.HttpException
import java.net.UnknownHostException
import java.util.ArrayList
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    @Inject
    lateinit var mAuthViewModelFactory: AuthViewModelFactory

    private val mAuthViewModel: AuthViewModel
        get() = getViewModel(mAuthViewModelFactory)

    private var SPLASH_TIME_OUT: Long? = null

    private val checkPermissionDialog = CheckPermissionDialog()
    private val messageDialog_success = MessageDialog("권한 허용이 완료되었습니다.","","확인",false,null)
    private val messageDialog_fail = MessageDialog("권한을 허용해 주세요.","권한이 허용되지 않으면 서비스 이용이 불가합니다.","확인",false,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindingData(BR.globalValue, GlobalValue)
        start()
    }

    private fun start() {
        SPLASH_TIME_OUT = 2000 // 2초
        Handler().postDelayed({
            if (!checkPermission()) showPermissionNoticeDialog()
            else checkToken()
        }, SPLASH_TIME_OUT!!)
    }

    private val permissionListener : PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            messageDialog_success.initEventObserver(object :MessageEventObserver{
                override fun onClickHelpBtn() { }

                override fun onClickOkBtn() {
                    messageDialog_success.dismiss()
                    checkPermissionDialog.dismiss()
                    checkToken()
                }
            })
            messageDialog_success.isCancelable = false
            messageDialog_success.show(supportFragmentManager)
        }

        override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {

            messageDialog_fail.initEventObserver(object :MessageEventObserver{
                override fun onClickHelpBtn() { }

                override fun onClickOkBtn() {
                    messageDialog_fail.dismiss()
                }

            })
            messageDialog_fail.isCancelable = false
            messageDialog_fail.show(supportFragmentManager)
        }

    }

    private fun showPermissionNoticeDialog(){
        checkPermissionDialog.initEventObserver(object : CheckPermissionEventObserver {
            override fun onClickOkBtn() {
                showPermissionCheckDialog()
            }
        })
        checkPermissionDialog.isCancelable = false
        checkPermissionDialog.show(supportFragmentManager)
    }

    private fun showPermissionCheckDialog() {
        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setPermissions(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ).check()
    }

    private fun checkToken(){
        isLoading.value = true
        mAuthViewModel.checkToken(object : DisposableCompletableObserver() {
            override fun onComplete() {
                isLoading.value = false
                moveToMainActivity()
            }

            override fun onError(e: Throwable) {
                isLoading.value = false
                when (e) {
                    is EmptyResultSetException -> logOut()
                    is HttpException -> when(e.code()) {
                        200 -> moveToMainActivity()
                        else -> logOut()
                    }
                    is UnknownHostException -> moveToMainActivity()
                }
            }

        })
    }

    private fun moveToMainActivity(){
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    /**
     *  true : 권한허용O
     *  false : 권한허용X
     */
    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }
}
