package kr.hs.dgsw.avocatalk.base

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import kr.hs.dgsw.avocatalk.R
import kr.hs.dgsw.avocatalk.data.exception.TokenException
import kr.hs.dgsw.avocatalk.data.widget.GlobalValue
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

abstract class BaseActivity<VB : ViewDataBinding> : DaggerAppCompatActivity() {
    protected lateinit var mBinding: VB

    protected open fun setDataBinding() {}
    protected open fun observerLiveData() {
        GlobalValue.onErrorEvent.observe(this, androidx.lifecycle.Observer {
            onErrorEvent(it)
        })
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mViewModel.onErrorEvent.observe(this, androidx.lifecycle.Observer { onErrorEvent(it) })
        //Todo onErrorEvent 처리.
        if (!::mBinding.isInitialized) mBinding = DataBindingUtil.setContentView(this, layoutRes())
        setDataBinding()
        observerLiveData()
    }

    protected open fun onErrorEvent(e: Throwable) {
        if (e is TokenException) {
            logOut()
            return
        }
    }

    protected fun logOut() {
        //Todo db에 저장된 모든 정보 삭제(키보드 높이 재외), 로그인 페이지로 이동.
    }

    protected fun initBindingData(br: Int,data: Any) {
        mBinding.setVariable(br, data)
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