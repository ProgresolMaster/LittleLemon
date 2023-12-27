package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.Typography

@Composable
fun Profile(navController: NavController) {
    val (firstName, lastName, email) = getDataFromSharedPreferences()
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .height(64.dp)
                    .width(256.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 56.dp)
        ) {
            Text(
                text = stringResource(id = R.string.home_legend),
                style = Typography.titleLarge,
                color = Color(0xFF000000),
                modifier = Modifier.padding(bottom = 24.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
            )
            Text(
                text = "First name",
                style = Typography.titleLarge,
                color = Color(0xFF000000),
                modifier = Modifier.padding(bottom = 4.dp),
            )
            Text(
                text = firstName,
                style = Typography.titleLarge,
                color = Color(0xFF000000),
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Last name",
                style = Typography.titleLarge,
                color = Color(0xFF000000),
                modifier = Modifier.padding(bottom = 4.dp),
            )

            Text(
                text = lastName,
                style = Typography.titleLarge,
                color = Color(0xFF000000),
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Email",
                style = Typography.titleLarge,
                color = Color(0xFF000000),
                modifier = Modifier.padding(bottom = 4.dp),
            )

            Text(
                text = email,
                style = Typography.titleLarge,
                color = Color(0xFF000000),
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
        ) {
            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    val sharedPreferences =
                        context.getSharedPreferences("LITTLE_LEMON", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.clear()
                    editor.apply()
                    navController.navigate(Onboarding.route)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(0.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFFF4CE14)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.btn_logout),
                    color = Color(0xFF000000),
                    style = Typography.titleLarge
                )
            }
        }

    }
}
