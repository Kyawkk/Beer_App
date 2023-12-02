package com.kyawzinlinn.beerapp.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kyawzinlinn.beerapp.data.entities.BeerEntity
import java.io.Serializable

@Composable
fun DetailScreen(
    beer: BeerEntity,
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        Box (
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Spacer(
                modifier = Modifier.fillMaxWidth()
                    .height(250.dp)
                    .align(Alignment.TopCenter)
                    .background(MaterialTheme.colorScheme.primary)
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(beer.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null
            )
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(32.dp))
            Text(
                text = beer.name?.toUpperCase() ?: "",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = beer.tagline?.toUpperCase() ?: ""
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "ABV ${beer.abv}",
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = beer.description ?: "",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}