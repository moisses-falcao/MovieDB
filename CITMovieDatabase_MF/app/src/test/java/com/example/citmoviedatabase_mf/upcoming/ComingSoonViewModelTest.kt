package com.example.citmoviedatabase_mf.upcoming

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.Results
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepository
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonStatus
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class ComingSoonViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: ComingSoonViewModel

    @Test
    fun `GIVEN a fake list of movies WHEN getting this list THEN assent Success`(){

        //Given
        val repositorySuccess = MockRepository(ComingSoonStatus.Success(LIST_COMING_SOON))
        viewModel = ComingSoonViewModel(repositorySuccess)

        //When
        viewModel.getAllMoviesUpcoming()

        //Then
        assertEquals(COMING_SOON_VIEW_MODEL_STATUS, viewModel.status.value)
    }

    @Test
    fun `GIVEN a NotFound State WHEN getting this THEN assert NotFound`(){

        //Given
        val repositorySuccess = MockRepository(ComingSoonStatus.NotFound)
        viewModel = ComingSoonViewModel(repositorySuccess)

        //When
        viewModel.getAllMoviesUpcoming()

        //Then
        assertEquals(ComingSoonViewModelStatus.NotFound, viewModel.status.value)
    }

    @Test
    fun `GIVEN an error WHEN getting this error THEN assert Error`(){

        //Given
        val repositoryError = MockRepository(ComingSoonStatus.Error(ERROR))
        viewModel = ComingSoonViewModel(repositoryError)

        //When
        viewModel.getAllMoviesUpcoming()

        //Then
        assertEquals(ComingSoonViewModelStatus.Error(ERROR), viewModel.status.value)
    }

    companion object{
        val LIST_COMING_SOON: Results = Results(listOf(
            MovieModel(1, 1.0, "dfgs", "erfger", 2.2, "4t5432", "retgewr", "erte", "sdafwe", emptyList())))

        val COMING_SOON_VIEW_MODEL_STATUS = ComingSoonViewModelStatus.Success(LIST_COMING_SOON)

        val ERROR: Throwable = Exception("Hi There!")
    }
}

class MockRepository(private val results: ComingSoonStatus) : ComingSoonRepository{
    override fun getAllMoviesUpcoming(comingSoonStatus: (ComingSoonStatus) -> Unit) {
        comingSoonStatus(results)
    }
}