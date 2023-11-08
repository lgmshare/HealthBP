package com.obody.health.databases

import androidx.room.*
import com.obody.health.bean.BloodProfile

@Dao
interface BloodDao {

    @Insert
    suspend fun insert(list: List<BloodProfile>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(BpData: BloodProfile): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(BpData: BloodProfile): Int

    @Delete
    suspend fun delete(BpData: BloodProfile)

    @Delete
    suspend fun delete(BpData: List<BloodProfile>)

    @Query("DELETE FROM ${AppDatabase.T_BpData} where id=:id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM ${AppDatabase.T_BpData}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${AppDatabase.T_BpData} where id=:id")
    fun queryById(id: Int): BloodProfile?

    @Query("SELECT * FROM ${AppDatabase.T_BpData}")
    suspend fun queryAll(): List<BloodProfile>

}