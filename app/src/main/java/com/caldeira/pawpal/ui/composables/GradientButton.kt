package com.caldeira.pawpal.ui.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.caldeira.pawpal.R

@Composable
fun AnimatedGradientButton(modifier: Modifier, text: String, onClick: () -> Unit = {}) {
    val infiniteTransition = rememberInfiniteTransition()

   val animatedOffset by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFFF3848A), Color(0xFF6193E6), Color(0xFFF3848A)),
        start = Offset(animatedOffset, animatedOffset),
        end = Offset(animatedOffset + 500f, animatedOffset)
    )


    Button(
        colors = ButtonColors(Color.Transparent,Color.Transparent,Color.Transparent,Color.Transparent),
        onClick = { onClick.invoke() },
        modifier = modifier
            .background(brush = gradientBrush, shape = RoundedCornerShape(100))
    ) {
        Text(text, color = Color.White)
    }
}


@Preview(showBackground = true)
@Composable
private fun CatBreedsScreenPreview() {
    AnimatedGradientButton(Modifier, stringResource(R.string.favorites))
}