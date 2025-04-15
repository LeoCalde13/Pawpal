package com.caldeira.pawpal

import com.caldeira.pawpal.data.BreedsDao
import com.caldeira.pawpal.repository.CatsRepository
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CatsRepositoryTest {
    private val dao: BreedsDao = mockk(relaxed = true)
    private val SUT = CatsRepository(dao)

    @Test
    fun `test setBreedFavoriteState`() = runTest {
        val breedId = "breedId"
        val isFavorite = true

        SUT.setBreedFavoriteState(breedId, isFavorite)
        verify { dao.updateFavorite(breedId, isFavorite) }
    }

}