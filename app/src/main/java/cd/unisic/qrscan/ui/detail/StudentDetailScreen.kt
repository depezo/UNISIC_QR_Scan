package cd.unisic.qrscan.ui.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cd.unisic.qrscan.domain.model.Student
import cd.unisic.qrscan.ui.theme.DarkBlue
import cd.unisic.qrscan.ui.theme.LightGray
import cd.unisic.qrscan.ui.theme.MainGreen
import cd.unisic.qrscan.ui.theme.TextGray
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDetailScreen(
    student: Student,
    onBack: () -> Unit
) {
    Scaffold(
        //topBar = {
        //    TopAppBar(
        //        title = { Text("Fiche d'identification", color = Color.White) },
        //        navigationIcon = {
        //            IconButton(onClick = onBack) {
        //                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
        //            }
        //        },
        //        colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBlue)
        //    )
        //}
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = student.photo_url ?: "https://picsum.photos/seed/${student.id}/200",
                contentDescription = "Photo Student",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(10.dp))
                   .border(2.dp, DarkBlue, RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = student.fullName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBlue,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            StatusBadge(student.isPaid)

            Spacer(modifier = Modifier.height(32.dp))

            InfoCard(student)

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)
            ) {
                Text("Terminer", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun StatusBadge(isPaid: Boolean) {
    Surface(
        color = if (isPaid) MainGreen.copy(alpha = 0.1f) else Color.Red.copy(alpha = 0.1f),
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier.padding(top = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Icon(
                imageVector = if (isPaid) Icons.Default.CheckCircle else Icons.Default.Person,
                contentDescription = null,
                tint = if (isPaid) MainGreen else Color.Red,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (isPaid) "FRAIS SOLDÉS" else "FRAIS NON PAYÉS",
                color = if (isPaid) MainGreen else Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun InfoCard(student: Student) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = LightGray),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            InfoRow("Matricule", student.matricule ?: "---")
            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.3f))
            InfoRow("Promotion", student.promotion_name)
            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.3f))
            InfoRow("Faculté", student.faculty_name)
            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.3f))
            InfoRow("Département", student.department_name)
            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.3f))
            InfoRow("Année Acad.", student.academic_year ?: "Null")
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = TextGray, fontSize = 14.sp)
        Text(text = value, color = DarkBlue, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}
