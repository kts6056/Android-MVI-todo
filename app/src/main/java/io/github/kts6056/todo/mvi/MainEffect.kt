package io.github.kts6056.todo.mvi

import io.github.kts6056.core_mvi.Effect

sealed class MainEffect : Effect {
    data class ShowToast(val message: String) : MainEffect()
}
