package io.github.kts6056.todo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import io.github.kts6056.core_mvi.base.MVIActivity
import io.github.kts6056.todo.databinding.ActivityMainBinding
import io.github.kts6056.todo.mvi.MainEffect
import io.github.kts6056.todo.mvi.MainIntent
import io.github.kts6056.todo.mvi.MainState

class MainActivity : MVIActivity<ActivityMainBinding, MainIntent, MainState, MainEffect>() {
    override fun initializeBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            btnUp.setOnClickListener { setIntent(MainIntent.OnUpButtonClicked) }
            btnDown.setOnClickListener { setIntent(MainIntent.OnDownButtonClicked) }
        }
    }

    override fun processState(state: MainState) = binding {
        tvCount.text = state.counter.toString()
    }

    override fun processEffect(effect: MainEffect) {
        when (effect) {
            is MainEffect.ShowToast ->
                Toast.makeText(this, effect.message, Toast.LENGTH_SHORT).show()
        }
    }
}
