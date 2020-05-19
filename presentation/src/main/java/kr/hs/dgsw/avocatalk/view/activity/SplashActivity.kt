package kr.hs.dgsw.avocatalk.view.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kr.hs.dgsw.avocatalk.base.BaseActivity
import kr.hs.dgsw.avocatalk.data.exception.TokenException
import kr.hs.dgsw.avocatalk.databinding.ActivitySplashBinding
import kr.hs.dgsw.avocatalk.eventObserver.MessageDialogEventObserver
import kr.hs.dgsw.avocatalk.eventObserver.PermissionNoticeDialogEventObserver
import kr.hs.dgsw.avocatalk.view.dialog.PermissionNoticeDialog
import kr.hs.dgsw.avocatalk.view.dialog.MessageDialog
import kr.hs.dgsw.avocatalk.viewmodel.activity.SplashViewModel
import kr.hs.dgsw.avocatalk.viewmodelfactory.activity.SplashViewModelFactory
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>(), PermissionListener {

    @Inject
    lateinit var viewModelFactory: SplashViewModelFactory

    override val viewModel: SplashViewModel
        get() = getViewModel(viewModelFactory)

    private var SPLASH_TIME_OUT: Long? = null

    private val permissionNoticeDialog = PermissionNoticeDialog()
    private val messageDialog_success = MessageDialog("권한 허용이 완료되었습니다.","","확인",false,null)
    private val messageDialog_fail = MessageDialog("권한을 허용해 주세요.","권한이 허용되지 않으면 서비스 이용이 불가합니다.","확인",false,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start()
    }

    private fun start() {
        SPLASH_TIME_OUT = 2000 // 2초
        Handler().postDelayed({
            if (!checkPermission()) showPermissionNoticeDialog()
            else checkToken()
        }, SPLASH_TIME_OUT!!)
    }

    private fun showPermissionNoticeDialog(){
        permissionNoticeDialog.isCancelable = false
        permissionNoticeDialog.show(supportFragmentManager)
        permissionNoticeDialog.setEventObserver(object : PermissionNoticeDialogEventObserver{
            override fun onClickOkBtn() {
                showPermissionCheckDialog()
            }
        })
    }

    private fun showPermissionCheckDialog() {
        TedPermission.with(this)
            .setPermissionListener(this)
            .setPermissions(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ).check()
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

    private fun checkToken(){
        viewModel.checkToken()
    }

    private fun moveToMainActivity(){
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    private fun moveToLoginActivity(){
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }

    override fun observerViewModel() {
        super.observerViewModel()
        viewModel.successTokenCheck.observe(this, Observer {
            moveToMainActivity()
        })

        viewModel.onErrorEvent.observe(this, Observer {
            if(it is TokenException){
                moveToLoginActivity()
            }
        })
    }

    override fun onPermissionGranted() {
        messageDialog_success.setEventObserver(object :MessageDialogEventObserver{
            override fun onClickHelpBtn() { }

            override fun onClickOkBtn() {
                messageDialog_success.dismiss()
                permissionNoticeDialog.dismiss()
                checkToken()
            }
        })
        messageDialog_success.isCancelable = false
        messageDialog_success.show(supportFragmentManager)
    }

    override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {

        messageDialog_fail.setEventObserver(object :MessageDialogEventObserver{
            override fun onClickHelpBtn() { }

            override fun onClickOkBtn() {
                messageDialog_fail.dismiss()
            }

        })
        messageDialog_fail.isCancelable = false
        messageDialog_fail.show(supportFragmentManager)
    }

}
