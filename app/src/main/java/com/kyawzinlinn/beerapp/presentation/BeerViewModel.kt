package com.kyawzinlinn.beerapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kyawzinlinn.beerapp.data.DataSource
import com.kyawzinlinn.beerapp.data.entities.BeerEntity
import com.kyawzinlinn.beerapp.domain.usecase.GetBeerUseCase
import com.kyawzinlinn.beerapp.presentation.navigation.ScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(private val beerUseCase: GetBeerUseCase) : ViewModel() {
    var beerPagingDataFlow: Flow<PagingData<BeerEntity>> = beerUseCase().cachedIn(viewModelScope)

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun updateNavigationIconStatus(canNavigateBack: Boolean) {
        _uiState.update {
            it.copy(canNavigateBack = canNavigateBack)
        }
    }

    fun updateTopAppBarTitle(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    fun updateBeerDetails(beerEntity: BeerEntity){
        _uiState.update {
            it.copy(beerEntity = beerEntity)
        }
    }

}

data class UiState(
    val canNavigateBack: Boolean = false,
    val title: String = ScreenRoute.HOME.title,
    val beerEntity : BeerEntity = DataSource.defaultBeerEntity
)