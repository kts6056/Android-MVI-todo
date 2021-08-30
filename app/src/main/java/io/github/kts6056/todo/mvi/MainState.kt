package io.github.kts6056.todo.mvi

import io.github.kts6056.core_mvi.State

data class MainState(
    val counter: Int
) : State
