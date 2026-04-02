package cd.unisic.qrscan.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cd.unisic.qrscan.ui.theme.DarkBlue
import cd.unisic.qrscan.ui.theme.MainGreen
import cd.unisic.qrscan.ui.theme.MintGreen
import cd.unisic.qrscan.ui.theme.TextGray
import cd.unisic.qrscan.R
import cd.unisic.qrscan.ui.theme.BkgBlue
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    userName: String = "Mary Soyinka",
    onScanClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            )
            {
              // AsyncImage(
              //     model = "https://picsum.photos/seed/user/100",
              //     contentDescription = "Profile",
              //     modifier = Modifier
              //         .size(48.dp)
              //         .clip(CircleShape),
              //     contentScale = ContentScale.Crop
              // )
              // Spacer(modifier = Modifier.width(12.dp))
              // Text(
              //     text = userName,
              //     fontSize = 16.sp,
              //     fontWeight = FontWeight.SemiBold,
              //     color = DarkBlue
              // )
            }

            Spacer(modifier = Modifier.weight(0.5f))

            // Illustration
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                // Simplified representation of the illustration in the image
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(
                        model = R.mipmap.ic_launcher,
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Text content
            Text(
                text = "UNISIC QR Scan",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Vérifier le statut de l'étudiant en scannant son bordereau",
                fontSize = 15.sp,
                color = TextGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp),
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            // Action Button
            Button(
                onClick = onScanClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BkgBlue,
                    contentColor = DarkBlue
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text(
                    text = "Scanner",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
