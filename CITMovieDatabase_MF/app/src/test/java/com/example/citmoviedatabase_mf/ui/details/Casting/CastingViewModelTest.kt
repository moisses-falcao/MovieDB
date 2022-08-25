package com.example.citmoviedatabase_mf.ui.details.Casting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ciandt.service.models.CastAndCrewModel
import com.ciandt.service.models.CastModel
import com.example.citmoviedatabase_mf.repository.casting.CastingRepository
import com.example.citmoviedatabase_mf.repository.casting.CastingStatus
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

class CastingViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: CastingViewModel

    private var repository: CastingRepository = mockk()

    private val liveDataState: Observer<CastingViewModelStatus> = spyk()

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
    fun `GIVEN a fake casting list WHEN getting this list THEN assert Success`(){

        //Given
        coEvery{ repository.getMovieCredits(MOVIE_ID)} returns CastingStatus.Success(CASTING)

        viewModel = CastingViewModel(repository)
        viewModel.status.observeForever(liveDataState)

        //When
        viewModel.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(CastingViewModelStatus.Success(CASTING), viewModel.status.value)

        verify { liveDataState.onChanged(CastingViewModelStatus.Success(CASTING)) }
        coVerify { repository.getMovieCredits(MOVIE_ID) }
    }

    @Test
    fun `GIVEN a NotFound state WHEN getting this THEN assert NotFound`(){

        //Given
        coEvery{ repository.getMovieCredits(MOVIE_ID)} returns CastingStatus.NotFound

        viewModel = CastingViewModel(repository)
        viewModel.status.observeForever(liveDataState)

        //When
        viewModel.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(CastingViewModelStatus.NotFound, viewModel.status.value)

        verify { liveDataState.onChanged(CastingViewModelStatus.NotFound) }
        coVerify { repository.getMovieCredits(MOVIE_ID) }
    }

    @Test
    fun `GIVEN an error state WHEN getting this THEN assert Error`(){

        //Given
        coEvery{ repository.getMovieCredits(MOVIE_ID)} returns CastingStatus.Error(ERROR)

        viewModel = CastingViewModel(repository)
        viewModel.status.observeForever(liveDataState)

        //When
        viewModel.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(CastingViewModelStatus.Error(ERROR), viewModel.status.value)

        verify { liveDataState.onChanged(CastingViewModelStatus.Error(ERROR)) }
        coVerify { repository.getMovieCredits(MOVIE_ID) }
    }

    companion object{
        val MOVIE_ID = "1"

        val CASTING = CastModel(listOf(CastAndCrewModel(1, "a", "b", "c")))

        val ERROR: Throwable = Exception("Hi there!")
    }
}