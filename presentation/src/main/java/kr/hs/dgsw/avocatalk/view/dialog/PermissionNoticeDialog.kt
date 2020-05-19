package kr.hs.dgsw.avocatalk.view.dialog

import android.content.ContentValues
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kr.hs.dgsw.avocatalk.base.BaseDialog
import kr.hs.dgsw.avocatalk.databinding.DialogPermissionNoticeBinding
import kr.hs.dgsw.avocatalk.eventObserver.PermissionNoticeDialogEventObserver
import kr.hs.dgsw.avocatalk.viewmodel.dialog.PermissionNoticeViewModel

class PermissionNoticeDialog : BaseDialog<DialogPermissionNoticeBinding,PermissionNoticeViewModel>() {

    override val viewModel by viewModels<PermissionNoticeViewModel>()

    private var eventObserver:PermissionNoticeDialogEventObserver? = null

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, ContentValues.TAG)
    }

    fun setEventObserver(eventObserver: PermissionNoticeDialogEventObserver){
        this.eventObserver = eventObserver
    }

    override fun observerViewModel() {
        super.observerViewModel()
        viewModel.onClickOkBtnEvent.observe(this, Observer {
            eventObserver!!.onClickOkBtn()
        })
    }

}
