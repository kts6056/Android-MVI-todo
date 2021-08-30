package io.github.kts6056.core_mvi.base

import io.github.kts6056.core_mvi.Effect
import io.github.kts6056.core_mvi.Intent
import io.github.kts6056.core_mvi.State

interface MviView<I: Intent, S: State, E: Effect>{
    fun setIntent(intent: I)

    fun processState(state: S)

    fun processEffect(effect: E)
}
