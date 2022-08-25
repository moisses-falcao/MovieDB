package com.example.citmoviedatabase_mf.repository.Photos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ciandt.service.apiservice.MovieDatabaseService
import com.ciandt.service.models.PhotoModel
import com.ciandt.service.models.SceneModel
import com.example.citmoviedatabase_mf.models.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class PhotosRepositoryImplTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var repository: PhotosRepositoryImpl

    @Test
    fun `testingScenesSuccess`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieScenes(MOVIE_ID) } returns MOVIE_SCENES

        repository = PhotosRepositoryImpl(service)

        //When
        val result = repository.getMovieScenes(MOVIE_ID)

        //Then
        assertEquals(PhotosStatus.Success(MOVIE_SCENES), result)
        coVerify { service.getMovieScenes(MOVIE_ID) }
    }

    @Test
    fun `testingScenesNotFound`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieScenes(MOVIE_ID) } returns SceneModel(emptyList())

        repository = PhotosRepositoryImpl(service)

        //When
        val result = repository.getMovieScenes(MOVIE_ID)

        //Then
        assertEquals(PhotosStatus.NotFound, result)
        coVerify { service.getMovieScenes(MOVIE_ID) }
    }

    @Test
    fun `testingScenesError`() = runBlocking{

        //Given
        val service: MovieDatabaseService = mockk()
        coEvery { service.getMovieScenes(MOVIE_ID) } throws ERROR

        repository = PhotosRepositoryImpl(service)

        //When
        val result = repository.getMovieScenes(MOVIE_ID)

        //Then
        assertEquals(PhotosStatus.Error(ERROR), result)
        coVerify { service.getMovieScenes(MOVIE_ID) }
    }

    companion object{

        const val MOVIE_ID = "1"

        val MOVIE_SCENES = SceneModel(listOf(PhotoModel(1, "spqwq")))

        val ERROR: Throwable = Exception("Hi there!")
    }
}