package com.example.littlelemon

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.Typography

@ExperimentalMaterial3Api
@Composable
fun Onboarding(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color(0xFF495E57))
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.home_title),
                    style = Typography.titleLarge,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    color = Color(0xFFFFFFFF)
                )
            }
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
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "First name",
                style = Typography.titleLarge,
                color = Color(0xFF000000),
                modifier = Modifier.padding(bottom = 4.dp),
            )
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                placeholder = { Text("Type your first name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 24.dp)
            )
            Text(
                text = "Last name",
                style = Typography.titleLarge,
                color = Color(0xFF000000),
                modifier = Modifier.padding(bottom = 4.dp),
            )
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                placeholder = { Text("Type your last name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 24.dp)
            )
            Text(
                text = "Email",
                style = Typography.titleLarge,
                color = Color(0xFF000000),
                modifier = Modifier.padding(bottom = 4.dp),
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Example: unknown@domain.com") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
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
                    if (isValidInput(firstName, lastName, email)) {
                        saveDataToSharedPreferences(context, firstName, lastName, email)
                        showMessage(context, "Registration successful!")
                        navController.navigate(Home.route)
                    } else {
                        showMessage(context, "Registration unsuccessful. Please enter all data.")
                    }
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
                    text = stringResource(id = R.string.btn_register),
                    color = Color(0xFF000000),
                    style = Typography.titleLarge
                )
            }
        }

    }
}

private fun showMessage(context: Context, text: String) {
    Toast.makeText(
        context,
        text,
        Toast.LENGTH_SHORT
    ).show()
}

private fun isValidInput(firstName: String, lastName: String, email: String): Boolean {
    return firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()
}

private fun saveDataToSharedPreferences(
    context: Context,
    firstName: String,
    lastName: String,
    email: String
) {
    val sharedPreferences = context.getSharedPreferences("LITTLE_LEMON", MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    editor.putString("FIRST_NAME", firstName)
    editor.putString("LAST_NAME", lastName)
    editor.putString("EMAIL", email)

    editor.apply()
}
