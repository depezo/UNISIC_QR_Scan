package cd.unisic.qrscan.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Int,
    val name: String,
    val postname: String,
    val firstname: String,
    val sex: String,
    val matricule: String?,
    val code: String?,
    val card_number: String?,
    val photo_url: String?,
    val academic_payment_status: String,
    val academic_year: String?,
    val promotion_name: String,
    val department_name: String,
    val faculty_name: String,
    val faculty_sigle: String
) {
    val fullName: String get() = "$name $postname $firstname".trim()
    val isPaid: Boolean get() = academic_payment_status.lowercase() == "paid" || academic_payment_status.lowercase() == "soldé"
}
