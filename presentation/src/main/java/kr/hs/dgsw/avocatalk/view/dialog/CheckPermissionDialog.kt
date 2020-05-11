package kr.hs.dgsw.avocatalk.view.dialog

import android.content.ContentValues
import androidx.fragment.app.FragmentManager
import kr.hs.dgsw.avocatalk.BR
import kr.hs.dgsw.avocatalk.base.BaseDialog
import kr.hs.dgsw.avocatalk.databinding.DialogCheckPermissionBinding
import kr.hs.dgsw.avocatalk.eventobserver.dialog.CheckPermissionEventObserver

class CheckPermissionDialog() : BaseDialog<DialogCheckPermissionBinding>(){

    private var messageEventObserver:CheckPermissionEventObserver? = null

    override fun setDataBinding() {
        super.setDataBinding()

        initBindingData(BR.eventObserver,messageEventObserver)
    }

    fun initEventObserver(checkPermissionEventObserver: CheckPermissionEventObserver){
        this.messageEventObserver = checkPermissionEventObserver
    }

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, ContentValues.TAG)
    }
}
