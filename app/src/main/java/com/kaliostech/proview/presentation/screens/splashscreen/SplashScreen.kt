package com.kaliostech.proview.presentation.screens.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kaliostech.proview.R


@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val isFinished by viewModel.isSplashFinished.collectAsState()

    LaunchedEffect(isFinished) {
        if (isFinished) {
            navController.navigate("main") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation))
    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // Your app icon
                    contentDescription = "App Logo",
                    modifier = Modifier.size(250.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF000000), // Green
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 4.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        ) {
                            append("PR")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF5C74FD), // Red
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 4.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        ) {
                            append("O")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF000000), // Green
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 4.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        ) {
                            append("VIEW")
                        }
                    }
                )
            }

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 32.dp)
            )
        }
    }
}
