package au.vu.nit3213.history.data.remote

import retrofit2.http.*
import com.google.gson.annotations.SerializedName

interface ApiService {
    @POST("{campus}/auth")
    suspend fun login(
        @Path("campus") campus: String,
        @Body body: LoginRequest
    ): LoginResponse

    @GET("dashboard/{keypass}")
    suspend fun getDashboard(
        @Path("keypass") keypass: String
    ): DashboardResponse
}

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(@SerializedName("keypass") val keypass: String)

data class HistoryEntity(
    val event: String,
    val startYear: Int,
    val endYear: Int,
    val location: String,
    val keyFigure: String,
    val description: String
)

data class DashboardResponse(
    val entities: List<HistoryEntity>,
    val entityTotal: Int
)
