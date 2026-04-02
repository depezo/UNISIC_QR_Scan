package cd.unisic.qrscan.data.network

import android.util.Log
import cd.unisic.qrscan.BuildConfig
import cd.unisic.qrscan.domain.model.Student
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnisicApiService @Inject constructor(
    private val client: HttpClient
) {
    // Base URL from BuildConfig (defined in build.gradle.kts from local.properties)
    private val baseUrl = BuildConfig.BASE_URL

    suspend fun getStudentByHash(hash: String): Result<Student> {
        println("SCANNER_LOG: Fetching student with hash: $hash")
        return try {
            val response = client.get("$baseUrl/mobile/student/$hash") //281d86a11a2381eb4641fae6d18f4187
            println("SCANNER_LOG: Response status: ${response.status}")
            if (response.status.value in 200..299) {
                Result.success(response.body())
            } else {
                val errorMsg = "Étudiant non trouvé (${response.status})"
                println("SCANNER_LOG: $errorMsg")
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            val errorMsg = if (e.message?.contains("connect") == true) {
                "Impossible de se connecter au serveur. Vérifiez l'adresse IP."
            } else {
                "Erreur réseau: ${e.localizedMessage}"
            }
            Log.d("depezo", "Error binding camera $errorMsg")
            println("SCANNER_LOG: Exception: ${e.message}")
            Result.failure(Exception(errorMsg))
        }
    }
}
