package kr.hs.dgsw.avocatalk.view.dialog

import android.content.ContentValues
import android.content.Intent
import androidx.fragment.app.FragmentManager
import kr.hs.dgsw.avocatalk.BR
import kr.hs.dgsw.avocatalk.base.BaseActivity
import kr.hs.dgsw.avocatalk.base.BaseDialog
import kr.hs.dgsw.avocatalk.databinding.DialogMessageBinding
import kr.hs.dgsw.avocatalk.eventobserver.dialog.MessageEventObserver

class MessageDialog(
    private val title: String,
    private val message: String,
    private val isShowHelpBtn: Boolean,
    private val btnText: String,
    private val helpBtnText: String?,
    private val activity:BaseActivity<*>,
    private val isFinishParentActivity:Boolean
) : BaseDialog<DialogMessageBinding>(){

    override fun setDataBinding() {
        super.setDataBinding()

        mBinding.setVariable(BR.eventObserver, object : MessageEventObserver{
            override fun onClickOkBtn() {
                dismiss()
                if (isFinishParentActivity) activity.finish()
            }
        })

        mBinding.setVariable(BR.title, title)
        mBinding.setVariable(BR.message, message)
        mBinding.setVariable(BR.isShowHelpBtn, isShowHelpBtn)
        mBinding.setVariable(BR.btnText, btnText)
        mBinding.setVariable(BR.helpBtnText, helpBtnText)
    }

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, ContentValues.TAG)
    }
}
