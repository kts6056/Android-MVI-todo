package io.github.kts6056.core_mvi.base

import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import io.github.kts6056.core_mvi.Effect
import io.github.kts6056.core_mvi.Intent
import io.github.kts6056.core_mvi.State
import kotlinx.coroutines.flow.collect

abstract class MVIActivity<Binding : ViewBinding, I : Intent, S : State, E : Effect> : BaseActivity<Binding>(), MviView<I, S, E> {
    abstract val viewModel: MVIVIewModel<I, S, E>

    override fun setIntent(intent: I) {
        viewModel.setIntent(intent)
    }

    init {
        lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                processState(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.effect.collect {
                processEffect(it)
            }
        }
    }
}
