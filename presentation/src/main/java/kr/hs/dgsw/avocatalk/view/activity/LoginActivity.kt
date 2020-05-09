package kr.hs.dgsw.avocatalk.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import kr.hs.dgsw.avocatalk.BR
import kr.hs.dgsw.avocatalk.R
import kr.hs.dgsw.avocatalk.base.BaseActivity
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import kr.hs.dgsw.avocatalk.databinding.ActivityLoginBinding
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import kr.hs.dgsw.avocatalk.viewmodel.AuthViewModel
import kr.hs.dgsw.avocatalk.eventobserver.activity.LoginEventObserver
import kr.hs.dgsw.avocatalk.view.dialog.MessageDialog
import kr.hs.dgsw.avocatalk.viewmodelfactory.AuthViewModelFactory
import kr.hs.dgsw.avocatalk.widget.SimpleTextWatcher
import retrofit2.HttpException
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    @Inject
    lateinit var mAuthViewModelFactory: AuthViewModelFactory

    private val mAuthViewModel: AuthViewModel
        get() = getViewModel(mAuthViewModelFactory)

    override fun setDataBinding() {
        super.setDataBinding()

        initBindingData(BR.globalValue, GlobalValue)
        initBindingData(BR.email, "")
        initBindingData(BR.pw, "")

        initBindingData(BR.eventObserver, object :
            LoginEventObserver {

            override fun onClickLoginBtn() {
                when {
                    mBinding.email.isNullOrBlank() ->  mBinding.inputLayoutEmail.error = getString(R.string.error_msg_empty_email)
                    mBinding.pw.isNullOrBlank() -> mBinding.inputLayoutPassword.error = getString(R.string.error_msg_empty_pw)
                    else -> {
                        mAuthViewModel.sendLoginRequest(LoginRequest("${mBinding.email}${getString(R.string.text_school_email_address)}", mBinding.pw!!,true))
                        GlobalValue.isLoading.value = true
                    }
                }
            }

            override fun onClickRegisterBtn() {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        })
    }

    override fun observerLiveData() {
        super.observerLiveData()
        mAuthViewModel.loginSuccessEvent.observe(this, Observer {
            if(it) startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            else MessageDialog(
                "이메일 인증이 필요합니다!",
                getString(R.string.msg_success_register),
                true,
                "확인",
                "URL이 만료되었나요?",
                this,
                false
            ).show(supportFragmentManager)
        })
    }

    override fun onErrorEvent(e: Throwable) {
        super.onErrorEvent(e)
        if(e is HttpException){
            mBinding.inputLayoutPassword.error = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다."
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI(){
        mBinding.inputEmail.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mBinding.inputLayoutEmail.error = null
            }
        })

        mBinding.inputPassword.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mBinding.inputLayoutPassword.error = null
            }
        })
    }
}
