package com.cagrisahinoglu.rickandmortycompose.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Created by cagrisahinoglu on 3.07.2022.
 */
abstract class BaseViewModel<UIState>: ViewModel() {

    private var _uiState = MutableStateFlow(setDefaultUIState())
    val uiState = _uiState.asStateFlow()

    protected fun setState(state: UIState) {
        _uiState.value = state
    }

    abstract fun setDefaultUIState(): UIState
}