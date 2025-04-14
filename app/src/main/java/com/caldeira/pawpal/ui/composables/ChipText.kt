package com.caldeira.pawpal.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChipText(text: String, textColor: Color, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 2.dp, horizontal = 3.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 8.sp,
        )
    }
}

@Preview("ChipText")
@Composable
private fun ChipTextPreview() {
    ChipText("American Shorthair", Color.Black, Color.White)
}