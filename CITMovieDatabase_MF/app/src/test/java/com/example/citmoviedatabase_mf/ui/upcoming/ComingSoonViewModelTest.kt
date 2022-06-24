package com.example.citmoviedatabase_mf.ui.upcoming

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.Results
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonRepository
import com.example.citmoviedatabase_mf.repository.comingsoon.ComingSoonStatus
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
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception

@RunWith(JUnit4::class)
class ComingSoonViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: ComingSoonViewModel

    private val repository: ComingSoonRepository = mockk()

    private val liveDataState: Observer<ComingSoonViewModelStatus> = spyk()

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
    fun `GIVEN a fake list of movies WHEN getting this list THEN assent Success`(){

        //Given
        coEvery{ repository.getAllMoviesUpcoming() } returns ComingSoonStatus.Success(LIST_COMING_SOON)

        viewModel = ComingSoonViewModel(repository)
        viewModel.status.observeForever(liveDataState)

        //When
        viewModel.getAllMoviesUpcoming()

        //Then
        assertEquals(COMING_SOON_VIEW_MODEL_STATUS, viewModel.status.value)

        verify { liveDataState.onChanged(ComingSoonViewModelStatus.Success(LIST_COMING_SOON)) }
        coVerify { repository.getAllMoviesUpcoming() }
    }

    @Test
    fun `GIVEN a NotFound State WHEN getting this THEN assert NotFound`(){

        //Given
        coEvery { repository.getAllMoviesUpcoming() } returns ComingSoonStatus.NotFound

        viewModel = ComingSoonViewModel(repository)
        viewModel.status.observeForever(liveDataState)

        //When
        viewModel.getAllMoviesUpcoming()

        //Then
        assertEquals(ComingSoonViewModelStatus.NotFound, viewModel.status.value)

        verify { liveDataState.onChanged(ComingSoonViewModelStatus.NotFound) }
        coVerify { repository.getAllMoviesUpcoming()}
    }

    @Test
    fun `GIVEN an error WHEN getting this error THEN assert Error`(){

        //Given
        coEvery { repository.getAllMoviesUpcoming() } returns ComingSoonStatus.Error(ERROR)

        viewModel = ComingSoonViewModel(repository)
        viewModel.status.observeForever(liveDataState)

        //When
        viewModel.getAllMoviesUpcoming()

        //Then
        assertEquals(ComingSoonViewModelStatus.Error(ERROR), viewModel.status.value)

        verify{ liveDataState.onChanged(ComingSoonViewModelStatus.Error(ERROR)) }
        coVerify { repository.getAllMoviesUpcoming() }
    }

    companion object{
        val LIST_COMING_SOON: Results = Results(listOf(
            MovieModel(1, 1.0, "dfgs", "erfger", 2.2, "4t5432", "retgewr", "erte", "sdafwe", emptyList())))

        val COMING_SOON_VIEW_MODEL_STATUS = ComingSoonViewModelStatus.Success(LIST_COMING_SOON)

        val ERROR: Throwable = Exception("Hi There!")
    }
}