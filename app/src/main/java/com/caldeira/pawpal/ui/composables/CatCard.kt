package com.caldeira.pawpal.ui.composables

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.caldeira.pawpal.R
import com.caldeira.pawpal.model.CatDetails

@Composable
fun CatCard(
    innerPadding: PaddingValues,
    catDetails: CatDetails,
    onCardClicked: () -> Unit = {},
    onFavoriteClicked: () -> Unit = {},
) {
    Card(
        onClick = { onCardClicked.invoke() },
        colors = CardDefaults.cardColors(containerColor = Color.Gray),
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
            AsyncImage(model = catDetails.imageUrl, contentDescription = "Image of ${catDetails.name}",  contentScale = ContentScale.FillHeight)

            FavoriteButton(Modifier.align(Alignment.TopEnd), catDetails.isFavorite, onFavoriteClicked)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Bottom),
            ) {
                // TODO fix colors
                ChipText(stringResource(R.string.life_span_years, catDetails.lifeExpectancy), Color.White, Color.DarkGray)

                ChipText(catDetails.name, Color.Black, Color.White)

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun CatCardPreview() {
    CatCard(PaddingValues(2.dp), CatDetails("id", "American Shorthair", 15, true, imageUrl = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"))
}