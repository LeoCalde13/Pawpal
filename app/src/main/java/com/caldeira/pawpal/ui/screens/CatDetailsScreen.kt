package com.caldeira.pawpal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.caldeira.pawpal.ui.composables.FavoriteButton
import com.caldeira.pawpal.ui.viewmodels.CatDetailsViewModel

@Composable
fun CatDetailsScreen(viewmodel: CatDetailsViewModel = hiltViewModel()) {
    val outlineShape = RoundedCornerShape(topStartPercent = 15, topEndPercent = 15)
    val details = viewmodel.breedDetails.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = details.value?.imageUrl,
            contentDescription = "Image of ${details.value?.name}",
            contentScale = ContentScale.Fit
        )

        FavoriteButton(
            Modifier.align(Alignment.TopEnd),
            50.dp,
            details.value?.isFavorite ?: false
        ) {
            details.value?.let {
                viewmodel.setBreedIsFavorite(it.id, !it.isFavorite)
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxHeight(0.6f)
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
                )
                .padding(top = 20.dp),
        ) {
            val breedDetails = details.value ?: return
            with(breedDetails) {
                Text(
                    name,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )

                Text(
                    origin,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                )

                Text(
                    temperament,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )


                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 50.dp),
                    thickness = 2.dp
                )

                Text(
                    description,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 20.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CatDetailsPreview() {
    CatDetailsScreen()
}