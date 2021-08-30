package io.github.kts6056.core_mvi.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<Binding : ViewBinding> : AppCompatActivity() {
    private val binding: Binding by lazy { initializeBinding() }

    abstract fun initializeBinding(): Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    protected fun binding(action: Binding.() -> Unit) {
        binding.action()
    }
}
