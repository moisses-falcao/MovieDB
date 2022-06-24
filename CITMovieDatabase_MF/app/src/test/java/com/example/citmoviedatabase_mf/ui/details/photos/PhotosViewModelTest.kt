package com.example.citmoviedatabase_mf.ui.details.photos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.citmoviedatabase_mf.models.PhotoModel
import com.example.citmoviedatabase_mf.models.SceneModel
import com.example.citmoviedatabase_mf.repository.Photos.PhotosRepository
import com.example.citmoviedatabase_mf.repository.Photos.PhotosStatus
import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PhotosViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: PhotosViewModel

    private val repository: PhotosRepository = mockk()

    private val liveDataStatus: Observer<PhotosViewModelStatus> = spyk()

    //Seta dispatcher e executa tudo de forma síncrona
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN a fake photos list WHEN getting this list THEN assert Success`(){

        //Given
        coEvery { repository.getMovieScenes(MOVIE_ID) } returns PhotosStatus.Success(PHOTOS)

        viewModel = PhotosViewModel(repository)
        viewModel.status.observeForever(liveDataStatus)

        //When
        viewModel.getMovieScenes(MOVIE_ID)

        //Then
        assertEquals(PhotosViewModelStatus.Success(PHOTOS), viewModel.status.value)

        verify{ liveDataStatus.onChanged(PhotosViewModelStatus.Success(PHOTOS)) }
        coVerify { repository.getMovieScenes(MOVIE_ID) }
    }

    @Test
    fun `GIVEN a NotFound status WHEN getting this THEN assert NotFound`(){

        //Given
        coEvery { repository.getMovieScenes(MOVIE_ID) } returns PhotosStatus.NotFound

        viewModel = PhotosViewModel(repository)
        viewModel.status.observeForever(liveDataStatus)

        //When
        viewModel.getMovieScenes(MOVIE_ID)

        //Then
        assertEquals(PhotosViewModelStatus.NotFound, viewModel.status.value)

        verify{ liveDataStatus.onChanged(PhotosViewModelStatus.NotFound) }
        coVerify { repository.getMovieScenes(MOVIE_ID) }
    }

    @Test
    fun `GIVEN an error WHEN getting this error THEN assert Error`(){

        //Given
        coEvery { repository.getMovieScenes(MOVIE_ID) } returns PhotosStatus.Error(ERROR)

        viewModel = PhotosViewModel(repository)
        viewModel.status.observeForever(liveDataStatus)

        //When
        viewModel.getMovieScenes(MOVIE_ID)

        //Then
        assertEquals(PhotosViewModelStatus.Error(ERROR), viewModel.status.value)

        verify{ liveDataStatus.onChanged(PhotosViewModelStatus.Error(ERROR)) }
        coVerify { repository.getMovieScenes(MOVIE_ID) }
    }

    companion object{

        val MOVIE_ID = "1"

        val PHOTOS = SceneModel(listOf(PhotoModel(1, "a")))

        val ERROR = Exception()
    }
}