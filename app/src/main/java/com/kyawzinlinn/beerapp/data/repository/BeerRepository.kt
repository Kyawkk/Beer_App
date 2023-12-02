package com.kyawzinlinn.beerapp.data.repository

import androidx.paging.PagingData
import com.kyawzinlinn.beerapp.data.entities.BeerEntity
import kotlinx.coroutines.flow.Flow

interface BeerRepository {
    fun getAllBeers(): Flow<PagingData<BeerEntity>>
}