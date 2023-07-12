import com.nikosgiov.evaluationmobile.data.remote.LoginRequest
import com.nikosgiov.evaluationmobile.data.remote.LoginResponse
import com.nikosgiov.evaluationmobile.domain.model.Magazine
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("Access/Login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("Access/Books")
    suspend fun getMagazines(): List<Magazine>
}
