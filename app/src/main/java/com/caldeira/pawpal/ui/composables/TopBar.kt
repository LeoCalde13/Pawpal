package com.caldeira.pawpal.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caldeira.pawpal.R

@Composable
fun TopBar(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    0.0f to Color.White,
                    0.5f to Color.White,
                    1f to Color(1, 1, 1, 0)
                ),
            )
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 10.dp),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun TopBarPreview() {
    TopBar(stringResource(R.string.app_name))
}