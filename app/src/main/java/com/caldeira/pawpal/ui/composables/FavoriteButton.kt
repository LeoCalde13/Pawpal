package com.caldeira.pawpal.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.caldeira.pawpal.R

@Composable
fun FavoriteButton(
    modifier: Modifier,
    size: Dp,
    isFavorite: Boolean,
    onFavoriteClicked: () -> Unit
) {
    val drawable = if (isFavorite) R.drawable.like_on else R.drawable.like_off
    Image(
        painter = painterResource(id = drawable),
        contentDescription = stringResource(R.string.cd_favorite_button),
        modifier = modifier
            .padding(20.dp)
            .size(size)
            .clickable { onFavoriteClicked.invoke() }
    )
}

@Preview(showBackground = true)
@Composable
private fun FavoritePreview() {
    FavoriteButton(Modifier, 100.dp, true) { }
}

