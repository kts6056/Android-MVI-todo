package io.github.kts6056.todo.mvi

import io.github.kts6056.core_mvi.Intent

sealed class MainIntent : Intent {
    object OnUpButtonClicked : MainIntent()
    object OnDownButtonClicked : MainIntent()
}
