package kr.hs.dgsw.avocatalk.viewmodel.dialog

import kr.hs.dgsw.avocatalk.base.BaseViewModel
import kr.hs.dgsw.avocatalk.data.widget.SingleLiveEvent

class PermissionNoticeViewModel: BaseViewModel() {

    val onClickOkBtnEvent =
        SingleLiveEvent<Unit>()

    fun onClickOkBtn(){
        onClickOkBtnEvent.call()
    }
}