import com.nikosgiov.evaluationmobile.common.Resource
import com.nikosgiov.evaluationmobile.domain.model.LoginResult
import com.nikosgiov.evaluationmobile.domain.model.Magazine

interface ApiServiceRepository {
    suspend fun login(userId: String, password: String): LoginResult
    suspend fun getMagazines(accessToken: String, tokenType: String): Resource<List<Magazine>>
}
