package com.example.citmoviedatabase_mf.details.photos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.citmoviedatabase_mf.models.PhotoModel
import com.example.citmoviedatabase_mf.models.SceneModel
import com.example.citmoviedatabase_mf.repository.Photos.PhotosRepository
import com.example.citmoviedatabase_mf.repository.Photos.PhotosStatus
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PhotosViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: PhotosViewModel

    @Test
    fun `GIVEN a fake photos list WHEN getting this list THEN assert Success`(){

        //Given
        val repositorySuccess = MockRepository(PhotosStatus.Success(PHOTOS))
        viewModel = PhotosViewModel(repositorySuccess)

        //When
        viewModel.getMovieScenes(MOVIE_ID)

        //Then
        assertEquals(PhotosViewModelStatus.Success(PHOTOS), viewModel.status.value)
    }

    @Test
    fun `GIVEN a NotFound status WHEN getting this THEN assert NotFound`(){

        val repositoryNotFound = MockRepository(PhotosStatus.NotFound)
        viewModel = PhotosViewModel(repositoryNotFound)

        viewModel.getMovieScenes(MOVIE_ID)

        assertEquals(PhotosViewModelStatus.NotFound, viewModel.status.value)
    }

    @Test
    fun `GIVEN an error WHEN getting this error THEN assert Error`(){

        val repositoryError = MockRepository(PhotosStatus.Error(ERROR))
        viewModel = PhotosViewModel(repositoryError)

        viewModel.getMovieScenes(MOVIE_ID)

        assertEquals(PhotosViewModelStatus.Error(ERROR), viewModel.status.value)
    }

    companion object{

        val MOVIE_ID = "1"

        val PHOTOS = SceneModel(listOf(PhotoModel(1, "a")))

        val ERROR = Exception()
    }
}

class MockRepository(private val response: PhotosStatus) : PhotosRepository{
    override fun getMovieScenes(movieId: String, photosStatus: (PhotosStatus) -> Unit) {
        photosStatus(response)
    }
}