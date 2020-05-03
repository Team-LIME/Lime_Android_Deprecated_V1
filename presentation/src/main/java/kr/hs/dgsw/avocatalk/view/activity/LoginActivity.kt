package kr.hs.dgsw.avocatalk.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kr.hs.dgsw.avocatalk.BR
import kr.hs.dgsw.avocatalk.base.BaseActivity
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue.isLoading
import kr.hs.dgsw.avocatalk.databinding.ActivityLoginBinding
import kr.hs.dgsw.avocatalk.viewmodel.AuthViewModel
import kr.hs.dgsw.avocatalk.eventobserver.LoginEventObserver
import kr.hs.dgsw.avocatalk.viewmodelfactory.AuthViewModelFactory
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

//    val mAuthViewModel by viewModels<AuthViewModel>()

    @Inject
    lateinit var mAuthViewModelFactory: AuthViewModelFactory

    val mAuthViewModel: AuthViewModel
        get() = getViewModel(mAuthViewModelFactory)

    override fun setDataBinding() {
        super.setDataBinding()

        initBindingData(BR.globalValue, GlobalValue)
        initBindingData(BR.email, "")
        initBindingData(BR.pw, "")

        initBindingData(BR.eventObserver, object : LoginEventObserver {

            override fun onClickLoginBtn() {
                mAuthViewModel.sendLoginRequest(mBinding.email!!, mBinding.pw!!)
                isLoading.value = true
            }

            override fun onClickRegisterBtn() {
//                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                Log.e("RegisterBtn","Click")
            }
        })
    }

    override fun observerViewModel() {
        super.observerViewModel()
        mAuthViewModel.loginSuccessEvent.observe(this, Observer {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
