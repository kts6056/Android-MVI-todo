package io.github.kts6056.todo

import io.github.kts6056.core_mvi.base.MVIVIewModel
import io.github.kts6056.todo.mvi.MainEffect
import io.github.kts6056.todo.mvi.MainIntent
import io.github.kts6056.todo.mvi.MainState

class MainViewModel : MVIVIewModel<MainIntent, MainState, MainEffect>() {
    override fun createInitialState(): MainState = MainState(counter = 0)

    override fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.OnUpButtonClicked -> setState {
                copy(counter = counter + 1).apply {
                    if (counter != 0 && counter % 10 == 0) setEffect { MainEffect.ShowToast("10의 배수 입니다.") }
                }
            }
            is MainIntent.OnDownButtonClicked -> setState {
                copy(counter = counter - 1).apply {
                    if (counter != 0 && counter % 10 == 0) setEffect { MainEffect.ShowToast("10의 배수 입니다.") }
                }
            }
        }
    }
}
