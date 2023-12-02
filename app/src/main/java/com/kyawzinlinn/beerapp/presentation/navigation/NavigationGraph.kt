package com.kyawzinlinn.beerapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.kyawzinlinn.beerapp.presentation.BeerViewModel
import com.kyawzinlinn.beerapp.presentation.detail.DetailScreen
import com.kyawzinlinn.beerapp.presentation.home.HomeScreen

@Composable
fun NavigationGraph(
    viewModel: BeerViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val beerPagingItems = viewModel.beerPagingDataFlow.collectAsLazyPagingItems()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = ScreenRoute.HOME.title
    ) {
        composable(route = ScreenRoute.HOME.title) {
            viewModel.apply {
                updateTopAppBarTitle(ScreenRoute.HOME.title)
                updateNavigationIconStatus(false)
            }
            HomeScreen(
                beerPagingItems = beerPagingItems,
                onBeerItemClick = {
                    viewModel.updateBeerDetails(it)
                    navController.navigate(ScreenRoute.DETAIL.title)
                }
            )
        }

        composable(route = ScreenRoute.DETAIL.title) {
            val beer = uiState.beerEntity
            viewModel.apply {
                updateTopAppBarTitle(beer.name ?: ScreenRoute.DETAIL.title)
                updateNavigationIconStatus(true)
            }
            DetailScreen(beer = beer)
        }
    }
}