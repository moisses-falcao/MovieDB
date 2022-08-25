package com.example.citmoviedatabase_mf.repository.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ciandt.service.apiservice.MovieDatabaseService
import com.ciandt.service.models.*
import com.example.citmoviedatabase_mf.models.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class DetailsRepositoryImplTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var repository: DetailsRepositoryImpl

    @Test
    fun `testingDetailsSuccess`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieDetails(MOVIE_ID) } returns MOVIE_DETAILS

        repository = DetailsRepositoryImpl(service)

        //When
        val result = repository.getMovieDetails(MOVIE_ID)

        //Then
        assertEquals(DetailsStatus.SuccessDetails(MOVIE_DETAILS), result)
        coVerify { service.getMovieDetails(MOVIE_ID) }
    }

    @Test
    fun `testingDetailsError`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieDetails(MOVIE_ID) } throws ERROR

        repository = DetailsRepositoryImpl(service)

        //When
        val result = repository.getMovieDetails(MOVIE_ID)

        //Then
        assertEquals(DetailsStatus.Error(ERROR), result)
        coVerify { service.getMovieDetails(MOVIE_ID) }
    }

    //Casting
    @Test
    fun `testingCastingSuccess`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieCredits(MOVIE_ID) } returns MOVIE_CREDITS

        repository = DetailsRepositoryImpl(service)

        //When
        val result = repository.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(DetailsStatus.SuccessCredits(MOVIE_CREDITS), result)
        coVerify { service.getMovieCredits(MOVIE_ID) }
    }

    @Test
    fun `testingCastingNotFound`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieCredits(MOVIE_ID) } returns CastModel(emptyList())

        repository = DetailsRepositoryImpl(service)

        //When
        val result = repository.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(DetailsStatus.NotFound, result)
        coVerify { service.getMovieCredits(MOVIE_ID) }
    }

    @Test
    fun `testingCastingError`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieCredits(MOVIE_ID) } throws ERROR

        repository = DetailsRepositoryImpl(service)

        //When
        val result = repository.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(DetailsStatus.Error(ERROR), result)
        coVerify { service.getMovieCredits(MOVIE_ID) }
    }

    //Scenes
    @Test
    fun `testingScenesSuccess`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieScenes(MOVIE_ID) } returns MOVIE_SCENES

        repository = DetailsRepositoryImpl(service)

        //When
        val result = repository.getMovieScenes(MOVIE_ID)

        //Then
        assertEquals(DetailsStatus.SuccessScenes(MOVIE_SCENES), result)
        coVerify { service.getMovieScenes(MOVIE_ID) }
    }
    @Test
    fun `testingScenesNotFound`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieScenes(MOVIE_ID) } returns SceneModel(emptyList())

        repository = DetailsRepositoryImpl(service)

        //When
        val result = repository.getMovieScenes(MOVIE_ID)

        //Then
        assertEquals(DetailsStatus.NotFound, result)
        coVerify { service.getMovieScenes(MOVIE_ID) }
    }

    @Test
    fun `testingScenesError`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieScenes(MOVIE_ID) } throws ERROR

        repository = DetailsRepositoryImpl(service)

        //When
        val result = repository.getMovieScenes(MOVIE_ID)

        //Then
        assertEquals(DetailsStatus.Error(ERROR), result)
        coVerify { service.getMovieScenes(MOVIE_ID) }
    }


    companion object{

        val MOVIE_DETAILS: MovieDetailsModel = MovieDetailsModel(1, "dsfwe", "sdfds", 2.0, "sdfsd", emptyList(), 100)

        const val MOVIE_ID = "1"

        val MOVIE_CREDITS = CastModel(listOf(CastAndCrewModel(1, "dfz", "ars", "sdf")))

        val MOVIE_SCENES = SceneModel(listOf(PhotoModel(1, "spqwq")))

        val ERROR: Throwable = Exception("Hi there!")
    }
}