package au.vu.nit3213.history.data

import au.vu.nit3213.history.data.remote.ApiService
import au.vu.nit3213.history.data.remote.DashboardResponse
import au.vu.nit3213.history.data.remote.LoginRequest
import javax.inject.Inject
import javax.inject.Singleton

// A tiny wrapper so screens can react to loading / success / error
sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

// What the app needs from the network layer
interface Repository {
    suspend fun login(campus: String, username: String, password: String): Result<String> // returns keypass
    suspend fun loadHistory(keypass: String): Result<DashboardResponse>
}

// Concrete implementation using Retrofit ApiService (provided by Hilt)
@Singleton
class RepositoryImpl @Inject constructor(
    private val api: ApiService
) : Repository {

    override suspend fun login(campus: String, username: String, password: String): Result<String> =
        try {
            val res = api.login(campus, LoginRequest(username, password))
            Result.Success(res.keypass)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Login failed")
        }

    override suspend fun loadHistory(keypass: String): Result<DashboardResponse> =
        try {
            Result.Success(api.getDashboard(keypass))
        } catch (e: Exception) {
            Result.Error(e.message ?: "Dashboard failed")
        }
}
