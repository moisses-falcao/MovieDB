package com.example.citmoviedatabase_mf.repository.nowplaying

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ciandt.service.apiservice.MovieDatabaseService
import com.ciandt.service.models.MovieModel
import com.ciandt.service.models.Results
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class NowPlayingRepositoryImplTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var repository: NowPlayingRepositoryImpl

    @Test
    fun `testingSuccess`() = runBlocking {

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getAllMoviesNowPlaying() } returns LIST_NOW_PLAYING

        repository = NowPlayingRepositoryImpl(service)

        //When
        val result = repository.getAllMoviesNowPlaying()

        //Then
        assertEquals(NowPlayingStatus.Success(LIST_NOW_PLAYING), result)
        coVerify { service.getAllMoviesNowPlaying() }
    }

    @Test
    fun `testingNotFound`() = runBlocking {

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getAllMoviesNowPlaying() } returns Results(emptyList())

        repository = NowPlayingRepositoryImpl(service)

        //When
        val result = repository.getAllMoviesNowPlaying()

        //Then
        assertEquals(NowPlayingStatus.NotFound, result)
        coVerify { service.getAllMoviesNowPlaying() }
    }

    @Test
    fun `testingError`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getAllMoviesNowPlaying() } throws ERROR

        repository = NowPlayingRepositoryImpl(service)

        //When
        val result = repository.getAllMoviesNowPlaying()

        //Then
        assertEquals(NowPlayingStatus.Error(ERROR), result)
        coVerify { service.getAllMoviesNowPlaying() }
    }

    companion object{

        val LIST_NOW_PLAYING = Results(results = listOf(
            MovieModel(1, 1.0, "dfgs", "erfger", 2.2, "4t5432", "retgewr", "erte", "sdafwe", emptyList())
        ))

        val ERROR: Throwable = Exception("Hi There!")
    }
}