package cd.unisic.qrscan.data.repository

import cd.unisic.qrscan.data.network.UnisicApiService
import cd.unisic.qrscan.domain.model.Student
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepository @Inject constructor(
    private val apiService: UnisicApiService
) {
    suspend fun getStudentInfo(hash: String): Result<Student> =
        apiService.getStudentByHash(hash)
}
