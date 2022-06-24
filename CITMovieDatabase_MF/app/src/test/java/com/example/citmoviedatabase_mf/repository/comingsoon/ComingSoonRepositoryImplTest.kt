package com.example.citmoviedatabase_mf.repository.comingsoon

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.citmoviedatabase_mf.apiservice.MovieDatabaseService
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.Results
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ComingSoonRepositoryImplTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var repository: ComingSoonRepositoryImpl

    @Test
    fun `testingSuccess`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getAllMoviesUpcoming() } returns LIST_COMING_SOON

        repository = ComingSoonRepositoryImpl(service)

        //When
        val result = repository.getAllMoviesUpcoming()

        //Then
        assertEquals(ComingSoonStatus.Success(LIST_COMING_SOON), result)
        coVerify { service.getAllMoviesUpcoming()}
    }

    @Test
    fun `testingNotFound`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getAllMoviesUpcoming() } returns Results(emptyList())

        repository = ComingSoonRepositoryImpl(service)

        //When
        val result = repository.getAllMoviesUpcoming()

        //Then
        assertEquals(ComingSoonStatus.NotFound, result)
        coVerify { service.getAllMoviesUpcoming()}
    }

    @Test
    fun `testingError`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getAllMoviesUpcoming() } throws ERROR

        repository = ComingSoonRepositoryImpl(service)

        //When
        val result = repository.getAllMoviesUpcoming()

        //Then
        assertEquals(ComingSoonStatus.Error(ERROR), result)
        coVerify { service.getAllMoviesUpcoming()}
    }

    companion object{

        val LIST_COMING_SOON = Results(results = listOf(
            MovieModel(1, 1.0, "dfgs", "erfger", 2.2, "4t5432", "retgewr", "erte", "sdafwe", emptyList())
        ))

        val ERROR: Throwable = Exception("Hi There!")
    }
}