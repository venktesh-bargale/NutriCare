package com.nutricare.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nutricare.data.models.UserPreferences

@Dao
interface UserPreferencesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserPreferences(userPreferences: UserPreferences)

    @Query("SELECT * FROM user_preferences WHERE userId = :userId")
    fun getUserPreferencesByUserId(userId: String): LiveData<UserPreferences?>

    @Query("SELECT * FROM user_preferences WHERE userId = :userId")
    suspend fun getUserPreferencesSync(userId: String): UserPreferences?

    @Update
    suspend fun updateUserPreferences(userPreferences: UserPreferences)

    @Delete
    suspend fun deleteUserPreferences(userPreferences: UserPreferences)

    @Query("DELETE FROM user_preferences WHERE userId = :userId")
    suspend fun deleteUserPreferencesByUserId(userId: String)
}
