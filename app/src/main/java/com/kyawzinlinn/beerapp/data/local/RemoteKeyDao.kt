package com.kyawzinlinn.beerapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kyawzinlinn.beerapp.data.entities.RemoteKeyEntity

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKeyEntity: RemoteKeyEntity)

    @Query ("select * from remote_key where id = :id")
    suspend fun getById(id: String): RemoteKeyEntity

    @Query("delete from remote_key where id = :id")
    suspend fun deleteById(id: String)
}