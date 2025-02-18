package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center))
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        HorizontalDivider(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(Modifier.size(24.dp))
        SignUp()
        // Spacer(Modifier.size(24.dp))
    }
}

@Composable
fun SignUp() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Don't have an account?", fontSize = 12.sp, color = Color(0xFFB5B5B5))
        Text(
            text = "Sign Up.",
            Modifier.padding(horizontal = 8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun Body(modifier: Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isLoginEnabled by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        ImageLogo(Modifier.align(alignment = Alignment.CenterHorizontally))
        Spacer(Modifier.size(16.dp))
        Email(email) {
            email = it
            isLoginEnabled = enabledLogin(email, password)
        }
        Spacer(Modifier.size(4.dp))
        Password(password) {
            password = it
            isLoginEnabled = enabledLogin(email, password)
        }
        Spacer(Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(Modifier.size(16.dp))
        LoginButton(isLoginEnabled)
        Spacer(Modifier.size(16.dp))
        LoginDivider()
        Spacer(Modifier.size(32.dp))
        SocialLogin()
    }
}

@Composable
fun SocialLogin() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Social login",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Continue as Mauro Erben",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun LoginDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        HorizontalDivider(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun LoginButton(loginEnabled: Boolean) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
        enabled = loginEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Log In")
    }
}

fun enabledLogin(email: String, password: String): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = "Forgot password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9)
    )
}

@Composable
fun Email(value: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFFAFAFA),
            focusedContainerColor = Color(0xFFFAFAFA),
            unfocusedTextColor = Color(0xFFB2B2B2),
            focusedTextColor = Color(0xFFB2B2B2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun Password(value: String, onValueChange: (String) -> Unit) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFFAFAFA),
            focusedContainerColor = Color(0xFFFAFAFA),
            unfocusedTextColor = Color(0xFFB2B2B2),
            focusedTextColor = Color(0xFFB2B2B2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val image = if (passwordVisible) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(
                onClick = { passwordVisible = !passwordVisible }
            ) { Icon(imageVector = image, contentDescription = "show password") }
        },
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "logo",
        modifier = modifier
    )
}

@SuppressLint("ContextCastToActivity")
@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(imageVector = Icons.Default.Close,
        contentDescription = "close app",
        modifier = modifier.clickable { activity.finish() })
}
