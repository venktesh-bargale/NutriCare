package com.nutricare.data.repository

import androidx.lifecycle.LiveData
import com.nutricare.data.local.UserDao
import com.nutricare.data.models.User
import com.nutricare.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun getFirstUser(): LiveData<User?> = userDao.getFirstUser()

    suspend fun getUserById(userId: String): Result<User?> = withContext(Dispatchers.IO) {
        try {
            val user = userDao.getUserById(userId)
            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun insertUser(user: User): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            userDao.insertUser(user)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun updateUser(user: User): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            userDao.updateUser(user)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun deleteUser(user: User): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            userDao.deleteUser(user)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getAllUsers(): Result<List<User>> = withContext(Dispatchers.IO) {
        try {
            val users = userDao.getAllUsers()
            Result.Success(users)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getUserCount(): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val count = userDao.getUserCount()
            Result.Success(count)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun deleteAllUsers(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            userDao.deleteAllUsers()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
