package kr.hs.dgsw.avocatalk.view.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import dagger.android.support.DaggerDialogFragment
import kr.hs.dgsw.avocatalk.BR
import kr.hs.dgsw.avocatalk.R
import kr.hs.dgsw.avocatalk.databinding.DialogMessageBinding
import kr.hs.dgsw.avocatalk.eventObserver.MessageDialogEventObserver
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue

class MessageDialog(
    private val title: String,
    private val message: String,
    private val btnText: String,
    private val isShowHelpBtn: Boolean,
    private val helpBtnText: String?
) : DaggerDialogFragment() {

    lateinit var mBinding: DialogMessageBinding

    private var eventObserver:MessageDialogEventObserver? = null

    fun show(fragmentManager: FragmentManager) {
        val transaction = fragmentManager.beginTransaction()
        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        dialog.setCanceledOnTouchOutside(true)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.dialog_message, container, false
        )!!
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding.setVariable(BR.title, title)
        mBinding.setVariable(BR.message, message)
        mBinding.setVariable(BR.btnText, btnText)
        mBinding.setVariable(BR.isShowHelpBtn, isShowHelpBtn)
        mBinding.setVariable(BR.helpBtnText, helpBtnText)
        mBinding.setVariable(BR.globalValue,
            GlobalValue
        )
        mBinding.setVariable(BR.eventObserver, eventObserver)

        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
        super.onViewCreated(view, savedInstanceState)
    }

    fun setEventObserver(eventObserver:MessageDialogEventObserver){
        this.eventObserver = eventObserver
    }

    override fun dismiss() {
        super.dismiss()
        mBinding.unbind()
    }
}
