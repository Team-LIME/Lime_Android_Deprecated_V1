package kr.hs.dgsw.avocatalk.base

import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kr.hs.dgsw.avocatalk.BR
import kr.hs.dgsw.avocatalk.R
import kr.hs.dgsw.avocatalk.data.exception.NoConnectivityException
import kr.hs.dgsw.avocatalk.data.exception.NoInternetException
import kr.hs.dgsw.avocatalk.view.activity.LoginActivity
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

abstract class BaseActivity<VB : ViewDataBinding, VM: BaseViewModel> : DaggerAppCompatActivity() {
    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM

    protected abstract val viewModel: VM

    protected open fun observerViewModel(){
        GlobalValue.logoutEvent.observe(this, androidx.lifecycle.Observer {
            ActivityCompat.finishAffinity(this)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        })
        GlobalValue.disconnectInternetEvent.observe(this, androidx.lifecycle.Observer {
            if(it is NoConnectivityException)
                Snackbar.make(window.decorView.rootView, "인터넷 연결이 끊겼습니다. WI-FI나 데이터 연결을 확인해주세요", Snackbar.LENGTH_LONG).show()
            else if(it is NoInternetException)
                Snackbar.make(window.decorView.rootView, "서버와의 연결이 끊겼습니다. WI-FI나 데이터 연결을 확인해주세요", Snackbar.LENGTH_LONG).show()
        })
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
        observerViewModel()
    }

    private fun performDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, layoutRes())
        mViewModel = if (::mViewModel.isInitialized) mViewModel else viewModel
        mBinding.setVariable(BR.viewModel, mViewModel)
        mBinding.setVariable(BR.globalValue,
            GlobalValue
        )
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::mBinding.isInitialized) mBinding.unbind()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (VERSION.SDK_INT != VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
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

    inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(factory: ViewModelProvider.Factory): T =
        ViewModelProvider(this, factory)[T::class.java]

    inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(): T =
        ViewModelProvider(this)[T::class.java]
}