package com.example.citmoviedatabase_mf.repository.casting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ciandt.service.apiservice.MovieDatabaseService
import com.ciandt.service.models.CastAndCrewModel
import com.ciandt.service.models.CastModel
import com.example.citmoviedatabase_mf.models.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CastingRepositoryImplTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var repository: CastingRepositoryImpl

    @Test
    fun `testingSuccess`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieCredits(MOVIE_ID) } returns MOVIE_CREDITS

        repository = CastingRepositoryImpl(service)

        //When
        val result = repository.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(CastingStatus.Success(MOVIE_CREDITS), result)
    }

    @Test
    fun `testingNotFound`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieCredits(MOVIE_ID) } returns CastModel(emptyList())

        repository = CastingRepositoryImpl(service)

        //When
        val result = repository.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(CastingStatus.NotFound, result)
    }

    @Test
    fun `testingError`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieCredits(MOVIE_ID) } throws ERROR

        repository = CastingRepositoryImpl(service)

        //When
        val result = repository.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(CastingStatus.Error(ERROR), result)
    }

    companion object{

        const val MOVIE_ID = "1"

        val MOVIE_CREDITS = CastModel(listOf(CastAndCrewModel(1, "dfz", "ars", "sdf")))

        val ERROR: Throwable = Exception("Hi there!")
    }
}