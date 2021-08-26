package io.github.kts6056.core_mvi.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {
    private var bindingRef: Binding? = null

    abstract fun initializeBinding(): Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = initializeBinding().apply {
        bindingRef = this
    }.root

    protected fun binding(action: Binding.() -> Unit) {
        checkNotNull(bindingRef) {
            "${this.javaClass.simpleName} - ViewDataBinding is released"
        }.action()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingRef = null
    }
}
