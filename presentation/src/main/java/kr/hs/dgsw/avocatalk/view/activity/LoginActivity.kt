package kr.hs.dgsw.avocatalk.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kr.hs.dgsw.avocatalk.R
import kr.hs.dgsw.avocatalk.base.BaseActivity
import kr.hs.dgsw.avocatalk.data.exception.LoginException
import kr.hs.dgsw.avocatalk.databinding.ActivityLoginBinding
import kr.hs.dgsw.avocatalk.domain.request.LoginRequest
import kr.hs.dgsw.avocatalk.eventObserver.MessageDialogEventObserver
import kr.hs.dgsw.avocatalk.view.dialog.MessageDialog
import kr.hs.dgsw.avocatalk.viewmodel.activity.LoginViewModel
import kr.hs.dgsw.avocatalk.viewmodelfactory.activity.LoginViewModelFactory
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import kr.hs.dgsw.avocatalk.widget.SimpleTextWatcher
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>() {

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory

    override val viewModel: LoginViewModel
        get() = getViewModel(viewModelFactory)

    private var dialog:MessageDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    override fun observerViewModel() {
        super.observerViewModel()

        viewModel.onErrorEvent.observe(this, Observer {
            if(it is LoginException){
                mBinding.inputLayoutPassword.error = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다."
            }
        })

        viewModel.loginSuccessEvent.observe(this, Observer {
            if(it) startActivity(Intent(this, MainActivity::class.java))
            else showDialog()
        })

        viewModel.sendEmailSuccessEvent.observe(this, Observer {
            Snackbar.make(login_layout, "인증 URL이 전송되었습니다.", Snackbar.LENGTH_LONG).show()
            dialog!!.dismiss()
        })

        viewModel.sendEmailFailEvent.observe(this, Observer {
            Snackbar.make(login_layout, "인증 URL 전송에 실패하였습니다.", Snackbar.LENGTH_LONG).show()
            dialog!!.dismiss()
        })

        viewModel.onClickLoginBtnEvent.observe(this, Observer {
            when {
                viewModel.email.value.isNullOrBlank() ->  mBinding.inputLayoutEmail.error = getString(R.string.error_msg_empty_email)
                viewModel.pw.value.isNullOrBlank() -> mBinding.inputLayoutPassword.error = getString(R.string.error_msg_empty_pw)
                else -> {
                    viewModel.sendLoginRequest()
                    GlobalValue.isLoading.value = true
                }
            }
        })

        viewModel.onClickRegisterBtnEvent.observe(this, Observer {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.deleteToken()
    }

    private fun showDialog(){
        val dialog = MessageDialog(
            "이메일 인증이 필요합니다!",
            getString(R.string.msg_success_register),
            "확인",
            true,
            "URL이 만료되었나요?"
        )
        dialog.isCancelable = false
        dialog.show(supportFragmentManager)
        dialog.setEventObserver(object :MessageDialogEventObserver{
            override fun onClickHelpBtn() {
                viewModel.sendEmailRequest()
            }

            override fun onClickOkBtn() {
                viewModel.deleteToken()
                dialog.dismiss()
            }

        })

        this.dialog = dialog
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
