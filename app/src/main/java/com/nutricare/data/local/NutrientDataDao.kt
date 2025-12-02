package com.nutricare.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nutricare.data.models.NutrientData

@Dao
interface NutrientDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNutrientData(nutrientData: NutrientData)

    @Query("SELECT * FROM nutrient_data WHERE id = :id")
    suspend fun getNutrientDataById(id: String): NutrientData?

    @Query("SELECT * FROM nutrient_data WHERE userId = :userId AND date = :date")
    suspend fun getNutrientDataByDate(userId: String, date: String): NutrientData?

    @Query("SELECT * FROM nutrient_data WHERE userId = :userId ORDER BY date DESC")
    fun getNutrientDataByUserId(userId: String): LiveData<List<NutrientData>>

    @Query("SELECT * FROM nutrient_data WHERE userId = :userId ORDER BY date DESC LIMIT :limit")
    suspend fun getNutrientDataByUserIdLimit(userId: String, limit: Int): List<NutrientData>

    @Query("SELECT * FROM nutrient_data ORDER BY date DESC")
    suspend fun getAllNutrientData(): List<NutrientData>

    @Update
    suspend fun updateNutrientData(nutrientData: NutrientData)

    @Delete
    suspend fun deleteNutrientData(nutrientData: NutrientData)

    @Query("DELETE FROM nutrient_data WHERE userId = :userId")
    suspend fun deleteNutrientDataByUserId(userId: String)

    @Query("""
        SELECT * FROM nutrient_data 
        WHERE userId = :userId AND date BETWEEN :startDate AND :endDate
        ORDER BY date DESC
    """)
    suspend fun getNutrientDataRange(userId: String, startDate: String, endDate: String): List<NutrientData>
}
