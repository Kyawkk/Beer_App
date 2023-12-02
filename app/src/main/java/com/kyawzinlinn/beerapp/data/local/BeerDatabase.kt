package com.kyawzinlinn.beerapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kyawzinlinn.beerapp.data.entities.BeerEntity
import com.kyawzinlinn.beerapp.data.entities.RemoteKeyEntity

@Database(entities = [BeerEntity::class, RemoteKeyEntity::class], version = 2)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao
    abstract fun remoteKeyDao() : RemoteKeyDao
}