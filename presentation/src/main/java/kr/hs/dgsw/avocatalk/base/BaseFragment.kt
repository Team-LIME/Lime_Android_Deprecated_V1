package kr.hs.dgsw.avocatalk.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kr.hs.dgsw.avocatalk.BR
import kr.hs.dgsw.avocatalk.R
import kr.hs.dgsw.avocatalk.data.exception.NoConnectivityException
import kr.hs.dgsw.avocatalk.data.exception.NoInternetException
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import kr.hs.dgsw.avocatalk.view.activity.LoginActivity
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : DaggerFragment() {
    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM

    protected abstract val viewModel: VM

    protected open fun observerViewModel(){
        GlobalValue.logoutEvent.observe(requireActivity(), androidx.lifecycle.Observer {
            ActivityCompat.finishAffinity(requireActivity())
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        })

        GlobalValue.disconnectInternetEvent.observe(this, androidx.lifecycle.Observer {
            if(it is NoConnectivityException)
                Snackbar.make(requireView(), "인터넷 연결이 끊겼습니다. WI-FI나 데이터 연결을 확인해주세요", Snackbar.LENGTH_LONG).show()
            else if(it is NoInternetException)
                Snackbar.make(requireView(), "서버와의 연결이 끊겼습니다. WI-FI나 데이터 연결을 확인해주세요", Snackbar.LENGTH_LONG).show()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(
            inflater, layoutRes(), container, false)!!
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()

        observerViewModel()
    }

    private fun setUp() {
        mViewModel = if (::mViewModel.isInitialized) mViewModel else viewModel
        mBinding.setVariable(BR.viewModel, mViewModel)
        mBinding.setVariable(BR.globalValue,
            GlobalValue
        )
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    /**
     * Generic Type (Binding) class 를 가져와서 layout 파일명으로 변환 후 자동으로 Layout Resource 를 가져옴
     *
     * @return layout resource
     */
    @LayoutRes
    private fun layoutRes(): Int {
        val split = ((Objects.requireNonNull<Type>(javaClass.genericSuperclass) as ParameterizedType).actualTypeArguments[0] as Class<*>)
            .simpleName.replace("Binding$".toRegex(), "").split("(?<=.)(?=\\p{Upper})".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()

        val name = StringBuilder()

        for (i in split.indices) {
            name.append(split[i].toLowerCase(Locale.ROOT))
            if (i != split.size - 1)
                name.append("_")
        }

        try {
            return R.layout::class.java.getField(name.toString()).getInt(R.layout::class.java)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }

        return 0
    }

    inline fun <reified T : ViewModel> Fragment.getViewModel(factory: ViewModelProvider.Factory): T =
        ViewModelProvider(this, factory)[T::class.java]

    inline fun <reified T : ViewModel> Fragment.getViewModel(): T =
        ViewModelProvider(this)[T::class.java]
}