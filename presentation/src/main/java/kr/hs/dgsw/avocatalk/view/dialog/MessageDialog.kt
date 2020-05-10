package kr.hs.dgsw.avocatalk.view.dialog

import android.content.ContentValues
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import kr.hs.dgsw.avocatalk.BR
import kr.hs.dgsw.avocatalk.base.BaseDialog
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import kr.hs.dgsw.avocatalk.data.widget.SingleLiveEvent
import kr.hs.dgsw.avocatalk.databinding.DialogMessageBinding
import kr.hs.dgsw.avocatalk.eventobserver.dialog.MessageEventObserver

class MessageDialog(
    private val title: String,
    private val message: String,
    private val btnText: String,
    private val isShowHelpBtn: Boolean,
    private val helpBtnText: String?
) : BaseDialog<DialogMessageBinding>(){

    private var messageEventObserver:MessageEventObserver? = null

    override fun setDataBinding() {
        super.setDataBinding()

        initBindingData(BR.globalValue, GlobalValue)
        initBindingData(BR.eventObserver,messageEventObserver)
        initBindingData(BR.title, title)
        initBindingData(BR.message, message)
        initBindingData(BR.isShowHelpBtn, isShowHelpBtn)
        initBindingData(BR.btnText, btnText)
        initBindingData(BR.helpBtnText, helpBtnText)
    }

    fun initEventObserver(messageEventObserver: MessageEventObserver){
        this.messageEventObserver = messageEventObserver
    }

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, ContentValues.TAG)
    }
}
