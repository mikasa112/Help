package com.example.help.ui.lottie

import android.content.Context
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

/**
 * @author yuanan
 * @date 2023/5/8
 * @desc lottie动画
 */
@Composable
fun LottieLoadingView(
    context: Context,
    file: String,
    modifier: Modifier = Modifier,
    iterations: Int = 10
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(file))
    LottieAnimation(
        composition,
        modifier = modifier.defaultMinSize(300.dp),
        iterations = iterations
    )
}

/**
 * 登录界面动画
 */
@Composable
fun LottieLoginView(context: Context) {
    LottieLoadingView(
        context = context,
        file = "login.json",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}

@Composable
fun LottieWorkingLoadingView(context: Context) {
    LottieLoadingView(
        context = context,
        file = "working.json",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}