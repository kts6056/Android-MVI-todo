package io.github.kts6056.core_mvi.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.kts6056.core_mvi.Effect
import io.github.kts6056.core_mvi.Intent
import io.github.kts6056.core_mvi.State
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class MVIVIewModel<I : Intent, S : State, E : Effect> : ViewModel() {

    abstract fun createInitialState(): S

    private val initialState: S by lazy { createInitialState() }

    private val _intent: MutableSharedFlow<I> = MutableSharedFlow()
    val intent = _intent.asSharedFlow()

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _effect: Channel<E> = Channel()
    val effect = _effect.receiveAsFlow()

    private val currentState
        get() = state.value

    init {
        subscribeIntent()
    }

    private fun subscribeIntent() {
        viewModelScope.launch {
            intent.collect {
                processIntent(it)
            }
        }
    }

    /**
     * Intent를 처리 합니다.
     */
    abstract fun processIntent(intent: I)

    /**
     * 새로운 Intent를 방출합니다.
     */
    fun setIntent(intent: I) {
        val newIntent = intent
        viewModelScope.launch { _intent.emit(newIntent) }
    }

    /**
     * UI 상태를 업데이트 합니다.
     */
    protected fun setState(reduce: S.() -> S) {
        val newState = currentState.reduce()
        _state.value = newState
    }

    /**
     * Effect를 발생합니다.
     */
    protected fun setEffect(builder: () -> E) {
        val newEffect = builder()
        viewModelScope.launch { _effect.send(newEffect) }
    }
}
