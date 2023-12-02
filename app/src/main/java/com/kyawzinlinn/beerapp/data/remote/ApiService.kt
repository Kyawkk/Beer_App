package com.kyawzinlinn.beerapp.data.remote

import com.kyawzinlinn.beerapp.data.entities.BeerResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : List<BeerResponse>?
}