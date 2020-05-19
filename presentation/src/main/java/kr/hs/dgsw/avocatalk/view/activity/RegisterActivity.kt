package kr.hs.dgsw.avocatalk.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import kr.hs.dgsw.avocatalk.R
import kr.hs.dgsw.avocatalk.base.BaseActivity
import kr.hs.dgsw.avocatalk.databinding.ActivityRegisterBinding
import kr.hs.dgsw.avocatalk.eventObserver.MessageDialogEventObserver
import kr.hs.dgsw.avocatalk.view.dialog.MessageDialog
import kr.hs.dgsw.avocatalk.viewmodel.activity.RegisterViewModel
import kr.hs.dgsw.avocatalk.viewmodelfactory.activity.RegisterViewModelFactory
import kr.hs.dgsw.avocatalk.widget.SimpleTextWatcher
import retrofit2.HttpException
import javax.inject.Inject

class RegisterActivity : BaseActivity<ActivityRegisterBinding,RegisterViewModel>() {

    @Inject
    lateinit var viewModelFactory: RegisterViewModelFactory

    override val viewModel: RegisterViewModel
        get() = getViewModel(viewModelFactory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    override fun observerViewModel() {
        super.observerViewModel()

        viewModel.onErrorEvent.observe(this, Observer {
            if(it is HttpException && it.code() == 409){
                mBinding.inputLayoutEmail.error = getString(R.string.msg_of_no_use_email)
                mBinding.inputLayoutEmail.requestFocus()
            }
        })
        viewModel.onClickShowTerms1Event.observe(this, Observer {
            TODO("약관 Dialog 이동")
        })
        viewModel.onClickShowTerms2Event.observe(this, Observer {
            TODO("약관 Dialog 이동")
        })
        viewModel.onClickRegisterBtnEvent.observe(this, Observer {
            onClickRegisterBtn()
        })
        viewModel.registerSuccessEvent.observe(this, Observer {
            showDialog()
        })
    }

    private fun initUI(){
        mBinding.inputName.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mBinding.inputLayoutPassword.error = null
            }
        })

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


        mBinding.inputCheckPassword.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mBinding.inputLayoutPassword.error = null
            }
        })
    }

    private fun showDialog(){
        val dialog = MessageDialog(
            getString(R.string.text_success_register),
            getString(R.string.msg_success_register),
            getString(R.string.btn_ok),
            false,
            null
        )
        dialog.setEventObserver(object : MessageDialogEventObserver {
            override fun onClickOkBtn() {
                dialog.dismiss()
                finish()
            }

            override fun onClickHelpBtn() { }
        })
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, "")
    }

    private fun onClickRegisterBtn() {

        mBinding.inputLayoutName.error  = null
        mBinding.inputLayoutEmail.error  = null
        mBinding.inputLayoutPassword.error  = null
        mBinding.inputLayoutCheckPassword.error  = null
        mBinding.useAgreementCheckBox.error  = null


        when {
            viewModel.name.value.isNullOrBlank() -> {
                mBinding.inputLayoutName.error =
                    getString(R.string.error_msg_empty_name)
                mBinding.inputLayoutName.requestFocus()
            }

            viewModel.email.value.isNullOrBlank() -> {
                mBinding.inputLayoutEmail.error =
                    getString(R.string.error_msg_empty_email)
                mBinding.inputLayoutEmail.requestFocus()
            }

            viewModel.pw.value.isNullOrBlank() -> {
                mBinding.inputLayoutPassword.error =
                    getString(R.string.error_msg_empty_pw)
                mBinding.inputLayoutPassword.requestFocus()
            }

            viewModel.checkPw.value.isNullOrBlank() -> {
                mBinding.inputLayoutCheckPassword.error =
                    getString(R.string.error_msg_empty_pw)
                mBinding.inputLayoutCheckPassword.requestFocus()
            }

            !viewModel.checkPw.value.equals(viewModel.pw.value) -> {
                mBinding.inputLayoutCheckPassword.error =
                    getString(R.string.error_msg_not_match_pw)
                mBinding.inputLayoutCheckPassword.requestFocus()
            }

            !mBinding.useAgreementCheckBox.isChecked -> {
                mBinding.useAgreementCheckBox.error = "동의점요"
                mBinding.useAgreementCheckBox.requestFocus()
            }

            else -> { viewModel.sendRegisterRequest() }
        }
    }
}
