package com.caldeira.pawpal.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caldeira.pawpal.R

@Composable
fun CatCard(
    innerPadding: PaddingValues,
    name: String,
    isFavorite: Boolean,
    lifeExpectancy: Int? = null,
    onCardClicked: () -> Unit = {},
    onFavoriteClicked: () -> Unit = {},
) {
    Card(
        onClick = { onCardClicked.invoke() },
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(25),
        modifier = Modifier
            .size(height = 200.dp, width = 150.dp)
            .padding(innerPadding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // TODO change image when image api is added
            //AsyncImage(model = null, contentDescription = null)
            Image(
                painterResource(R.drawable.cat), "Cat",
                contentScale = ContentScale.FillHeight
            )

            FavoriteButton(Modifier.align(Alignment.TopEnd), isFavorite, onFavoriteClicked)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Bottom),
            ) {
                // TODO fix colors
                lifeExpectancy?.let {
                    ChipText(stringResource(R.string.life_span_years, lifeExpectancy), Color.White, Color.DarkGray)
                }

                ChipText(name, Color.Black, Color.White)

            }
        }

    }
}


@Composable
fun FavoriteButton(modifier: Modifier, isFavorite: Boolean, onFavoriteClicked: () -> Unit) {
    val drawable = if (isFavorite) R.drawable.like_on else R.drawable.like_off
    Image(
        painter = painterResource(id = drawable),
        contentDescription = stringResource(R.string.cd_favorite_button),
        modifier = modifier
            .padding(20.dp)
            .size(28.dp)
            .clickable { onFavoriteClicked() }
    )
}


@Preview(showBackground = true)
@Composable
private fun CatCardPreview() {
    CatCard(PaddingValues(2.dp), "American Shorthair", true, 15)
}


@Preview(showBackground = true)
@Composable
private fun FavoritePreview() {
    FavoriteButton(Modifier, true) { }
}


