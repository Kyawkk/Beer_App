@file:OptIn(ExperimentalPagingApi::class, ExperimentalPagingApi::class)

package com.kyawzinlinn.beerapp.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.kyawzinlinn.beerapp.data.entities.BeerEntity
import com.kyawzinlinn.beerapp.data.local.BeerDao
import com.kyawzinlinn.beerapp.data.local.BeerDatabase
import com.kyawzinlinn.beerapp.data.remote.ApiService
import com.kyawzinlinn.beerapp.data.remote.BeerRemoteMediator
import com.kyawzinlinn.beerapp.data.repository.BeerRepository
import com.kyawzinlinn.beerapp.data.repository.BeerRepositoryImpl
import com.kyawzinlinn.beerapp.utils.DEFAULT_PAGE_PER_COUNT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesBeerDatabase(@ApplicationContext context: Context): BeerDatabase {
        return Room.databaseBuilder(
            context, BeerDatabase::class.java, "beer_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesBeerDao(database: BeerDatabase): BeerDao {
        return database.beerDao()
    }

    @Provides
    @Singleton
    fun providesBeerPaging(
        database: BeerDatabase, apiService: ApiService
    ): Pager<Int, BeerEntity> {
        return Pager(config = PagingConfig(pageSize = DEFAULT_PAGE_PER_COUNT),
            remoteMediator = BeerRemoteMediator(
                database = database, apiService = apiService
            ),
            pagingSourceFactory = {
                database.beerDao().getAllBeers()
            })
    }

    @Provides
    fun providesBeerRepository(pager: Pager<Int, BeerEntity>): BeerRepository {
        return BeerRepositoryImpl(pager)
    }
}