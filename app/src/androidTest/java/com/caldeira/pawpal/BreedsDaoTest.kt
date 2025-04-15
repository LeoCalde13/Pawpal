package com.caldeira.pawpal

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.caldeira.pawpal.data.BreedsDao
import com.caldeira.pawpal.data.BreedsDatabase
import com.caldeira.pawpal.model.BreedDetailsEntity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val ID = "testId"

@RunWith(AndroidJUnit4::class)
class BreedsDaoTest {

    private lateinit var db: BreedsDatabase
    private lateinit var breedsDao: BreedsDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BreedsDatabase::class.java
        ).build()

        breedsDao = db.getBreedsDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testInsertAndGetAll() {
        val breed = getBreedDetailsEntity()
        breedsDao.insert(breed)

        val breeds = breedsDao.getAll()
        assertEquals(1, breeds.size)
        assertEquals(ID, breeds[0].id)
    }

    @Test
    fun testGetBreedById() {
        val breed = getBreedDetailsEntity()
        breedsDao.insert(breed)

        val retrievedBreed = breedsDao.getBreedById(ID)
        assertNotNull(retrievedBreed)
        assertEquals("Abyssinian", retrievedBreed?.name)
    }

    @Test
    fun testUpdateFavorite() {
        val breed = getBreedDetailsEntity()
        breedsDao.insert(breed)

        breedsDao.updateFavorite(ID, true)
        val updatedBreed = breedsDao.getBreedById(ID)

        assertNotNull(updatedBreed)
        assertTrue(updatedBreed?.isFavorite ?: false)
    }

    private fun getBreedDetailsEntity() = BreedDetailsEntity(
        id = ID,
        name = "Abyssinian",
        lifeExpectancy = 12,
        isFavorite = false,
        origin = "Egypt",
        temperament = "Active, Energetic, Independent, Intelligent, Gentle",
        description = "Abyssinian Description",
        imageUrl = "mockUrl"
    )
}