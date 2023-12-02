@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.kyawzinlinn.beerapp.presentation.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kyawzinlinn.beerapp.data.entities.BeerEntity
import com.kyawzinlinn.beerapp.presentation.components.ErrorScreen
import com.kyawzinlinn.beerapp.utils.ErrorHandler

@Composable
fun HomeScreen(
    beerPagingItems: LazyPagingItems<BeerEntity>,
    onBeerItemClick: (BeerEntity) -> Unit
) {

    when (beerPagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is LoadState.NotLoading -> BeerList(
            pagingItems = beerPagingItems,
            onBeerItemClick = onBeerItemClick
        )

        is LoadState.Error -> ErrorScreen(
            message = ErrorHandler.handle((beerPagingItems.loadState.refresh as LoadState.Error).error),
            onRetry = {
                beerPagingItems.refresh()
            }
        )
    }
}

@Composable
private fun BeerList(
    pagingItems: LazyPagingItems<BeerEntity>,
    onBeerItemClick: (BeerEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.id }
        ) { index ->
            BeerItem(
                beer = pagingItems[index]!!,
                onBeerItemClick = onBeerItemClick
            )
        }
        if (pagingItems.loadState.append is LoadState.Loading) {
            item {
                Box (
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = modifier.size(24.dp))
                }
            }
        }
    }
}

@Composable
private fun BeerItem(
    beer: BeerEntity,
    onBeerItemClick: (BeerEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(modifier = modifier.fillMaxWidth(), onClick = { onBeerItemClick(beer) }) {
        Row(
            modifier = modifier
                .padding(16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(beer.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .weight(0.2f)
                    .height(120.dp)

            )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(0.8f)) {
                Text(
                    text = beer.name ?: "",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = beer.tagline?.toUpperCase() ?: "",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = beer.description ?: "",
                    maxLines = 3,
                    style = MaterialTheme.typography.labelLarge,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.width(8.dp))
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}