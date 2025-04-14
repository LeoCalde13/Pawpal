package com.caldeira.pawpal.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caldeira.pawpal.EMPTY_STRING
import com.caldeira.pawpal.R

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (input: String) -> Unit = { }
) {
    val outlineShape = RoundedCornerShape(100)

    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .border(
                width = 2.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF6193E6), Color(0xFFF3848A))
                ),
                shape = outlineShape
            )
            .background(
                color = Color.White,
                shape = outlineShape
            ),
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                Modifier
                    .padding(vertical = 15.dp, horizontal = 20.dp)
            ) {
                // Placeholder
                if (value.isEmpty()) {
                    Text(
                        text = stringResource(R.string.search_placeholder),
                        color = Color.Gray,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }

                innerTextField()
            }
        }
    )
}

@Preview(showBackground = false)
@Composable
private fun EditBoxPreview() {
    SearchBox(
        modifier = Modifier,
        value = EMPTY_STRING,
        onValueChange = { }
    )
}