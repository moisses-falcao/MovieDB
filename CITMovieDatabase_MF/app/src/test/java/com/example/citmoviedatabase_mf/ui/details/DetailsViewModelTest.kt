package com.example.citmoviedatabase_mf.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.citmoviedatabase_mf.models.*
import com.example.citmoviedatabase_mf.repository.details.DetailsRepository
import com.example.citmoviedatabase_mf.repository.details.DetailsStatus
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
class DetailsViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailsViewModel

    private var repository: DetailsRepository = mockk()

    private var liveDataStatus: Observer<DetailsViewModelStatus> = spyk()

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

    //Details
    @Test
    fun `GIVEN a fake movie details WHEN getting this fake movie details THEN assert Success`(){

        //Given
        coEvery { repository.getMovieDetails(MOVIE_ID) } returns DetailsStatus.SuccessDetails(MOVIE_DETAILS)

        viewModel = DetailsViewModel(repository)
        viewModel.statusDetails.observeForever(liveDataStatus)

        //WHEN
        viewModel.getMovieDetails(MOVIE_ID)

        //THEN
        assertEquals(DetailsViewModelStatus.SuccessDetails(MOVIE_DETAILS), viewModel.statusDetails.value)

        verify { liveDataStatus.onChanged(DetailsViewModelStatus.SuccessDetails(MOVIE_DETAILS)) }
        coVerify { repository.getMovieDetails(MOVIE_ID) }
    }

    @Test
    fun `GIVEN a NotFound list WHEN getting this THEN assert NotFound`(){

        //Given
        coEvery { repository.getMovieDetails(MOVIE_ID) } returns DetailsStatus.NotFound

        viewModel = DetailsViewModel(repository)
        viewModel.statusDetails.observeForever(liveDataStatus)

        //WHEN
        viewModel.getMovieDetails(MOVIE_ID)

        //THEN
        assertEquals(DetailsViewModelStatus.NotFound, viewModel.statusDetails.value)

        verify { liveDataStatus.onChanged(DetailsViewModelStatus.NotFound) }
        coVerify { repository.getMovieDetails(MOVIE_ID) }
    }

    @Test
    fun `GIVEN an error WHEN getting this error THEN assert Error`(){

        //Given
        coEvery { repository.getMovieDetails(MOVIE_ID) } returns DetailsStatus.Error(ERROR)

        viewModel = DetailsViewModel(repository)
        viewModel.statusDetails.observeForever(liveDataStatus)

        //WHEN
        viewModel.getMovieDetails(MOVIE_ID)

        //THEN
        assertEquals(DetailsViewModelStatus.Error(ERROR), viewModel.statusDetails.value)

        verify { liveDataStatus.onChanged(DetailsViewModelStatus.Error(ERROR)) }
        coVerify { repository.getMovieDetails(MOVIE_ID) }
    }

    //Credits
    @Test
    fun `GIVEN a fake movie casting WHEN getting this fake casting THEN assert Success`(){

        //Given
        coEvery { repository.getMovieCredits(MOVIE_ID) } returns DetailsStatus.SuccessCredits(MOVIE_CREDITS)

        viewModel = DetailsViewModel(repository)
        viewModel.statusCredits.observeForever(liveDataStatus)

        //WHEN
        viewModel.getMovieCredits(MOVIE_ID)

        //THEN
        assertEquals(DetailsViewModelStatus.SuccessCredits(MOVIE_CREDITS), viewModel.statusCredits.value)

        verify { liveDataStatus.onChanged(DetailsViewModelStatus.SuccessCredits(MOVIE_CREDITS)) }
        coVerify { repository.getMovieCredits(MOVIE_ID) }
    }

    @Test
    fun `GIVEN a NotFound casting list WHEN getting this list THEN assert NotFound`(){

        //Given
        coEvery { repository.getMovieCredits(MOVIE_ID) } returns DetailsStatus.NotFound

        viewModel = DetailsViewModel(repository)
        viewModel.statusCredits.observeForever(liveDataStatus)

        //WHEN
        viewModel.getMovieCredits(MOVIE_ID)

        //THEN
        assertEquals(DetailsViewModelStatus.NotFound, viewModel.statusCredits.value)

        verify { liveDataStatus.onChanged(DetailsViewModelStatus.NotFound) }
        coVerify { repository.getMovieCredits(MOVIE_ID) }
    }

    @Test
    fun `GIVEN a casting error WHEN getting this error THEN assert Error`(){

        //Given
        coEvery { repository.getMovieCredits(MOVIE_ID) } returns DetailsStatus.Error(ERROR)

        viewModel = DetailsViewModel(repository)
        viewModel.statusCredits.observeForever(liveDataStatus)

        //WHEN
        viewModel.getMovieCredits(MOVIE_ID)

        //THEN
        assertEquals(DetailsViewModelStatus.Error(ERROR), viewModel.statusCredits.value)

        verify { liveDataStatus.onChanged(DetailsViewModelStatus.Error(ERROR)) }
        coVerify { repository.getMovieCredits(MOVIE_ID) }
    }

    //Scenes
    @Test
    fun `GIVEN a fake movie scenes WHEN getting this fake scenes THEN assert Success`(){

        //Given
        coEvery { repository.getMovieScenes(MOVIE_ID) } returns DetailsStatus.SuccessScenes(MOVIE_SCENES)

        viewModel = DetailsViewModel(repository)
        viewModel.statusScenes.observeForever(liveDataStatus)

        //WHEN
        viewModel.getMovieScenes(MOVIE_ID)

        //THEN
        assertEquals(DetailsViewModelStatus.SuccessScenes(MOVIE_SCENES), viewModel.statusScenes.value)

        verify { liveDataStatus.onChanged(DetailsViewModelStatus.SuccessScenes(MOVIE_SCENES)) }
        coVerify { repository.getMovieScenes(MOVIE_ID) }
    }

    @Test
    fun `GIVEN a NotFound scenes list WHEN getting this list THEN assert NotFound`(){

        //Given
        coEvery { repository.getMovieScenes(MOVIE_ID) } returns DetailsStatus.NotFound

        viewModel = DetailsViewModel(repository)
        viewModel.statusScenes.observeForever(liveDataStatus)

        //WHEN
        viewModel.getMovieScenes(MOVIE_ID)

        //THEN
        assertEquals(DetailsViewModelStatus.NotFound, viewModel.statusScenes.value)

        verify { liveDataStatus.onChanged(DetailsViewModelStatus.NotFound) }
        coVerify { repository.getMovieScenes(MOVIE_ID) }
    }

    @Test
    fun `GIVEN a scenes error WHEN getting this error THEN assert Error`(){

        //Given
        coEvery { repository.getMovieScenes(MOVIE_ID) } returns DetailsStatus.Error(ERROR)

        viewModel = DetailsViewModel(repository)
        viewModel.statusScenes.observeForever(liveDataStatus)

        //WHEN
        viewModel.getMovieScenes(MOVIE_ID)

        //THEN
        assertEquals(DetailsViewModelStatus.Error(ERROR), viewModel.statusScenes.value)

        verify { liveDataStatus.onChanged(DetailsViewModelStatus.Error(ERROR)) }
        coVerify { repository.getMovieScenes(MOVIE_ID) }
    }

    companion object{

        val MOVIE_DETAILS: MovieDetailsModel = MovieDetailsModel(1, "dsfwe", "sdfds", 2.0, "sdfsd", emptyList(), 100)

        const val MOVIE_ID = "1"

        val MOVIE_CREDITS = CastModel(listOf(CastAndCrewModel(1, "dfz", "ars", "sdf")))

        val MOVIE_SCENES = SceneModel(listOf(PhotoModel(1, "spqwq")))

        val ERROR: Throwable = Exception("Hi there!")
    }
}