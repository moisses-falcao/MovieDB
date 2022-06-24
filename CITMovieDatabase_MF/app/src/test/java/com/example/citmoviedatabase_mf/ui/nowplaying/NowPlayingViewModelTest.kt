package com.example.citmoviedatabase_mf.ui.nowplaying

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.Results
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingRepository
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus
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

@RunWith(JUnit4::class)
class NowPlayingViewModelTes{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: NowPlayingViewModel

    private val repository: NowPlayingRepository = mockk()

    private val liveDataState: Observer<NowPlayingViewModelStatus> = spyk()

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
    fun `GIVEN a fake movie list WHEN getting movie list THEN assert Success`(){

        //Given
        coEvery { repository.getAllMoviesNowPlaying() } returns NowPlayingStatus.Success(LIST_NOW_PLAYING)

        viewModel = NowPlayingViewModel(repository)
        viewModel.status.observeForever(liveDataState)

        //When
        viewModel.getAllMoviesNowPlaying()

        //Then
        assertEquals(NowPlayingViewModelStatus.Success(LIST_NOW_PLAYING), viewModel.status.value) //Aqui o teste pode quebrar, somente o verify é suficiente

        verify { liveDataState.onChanged(NowPlayingViewModelStatus.Success(LIST_NOW_PLAYING)) }
        coVerify{ repository.getAllMoviesNowPlaying()}
    }

    @Test
    fun `GIVEN a NotFound State WHEN getting this THEN assert NotFound`(){

        //Given
        coEvery{repository.getAllMoviesNowPlaying()} returns NowPlayingStatus.NotFound

        viewModel = NowPlayingViewModel(repository)
        viewModel.status.observeForever(liveDataState)

        //When
        viewModel.getAllMoviesNowPlaying()

        //Then
        assertEquals(NowPlayingViewModelStatus.NotFound, viewModel.status.value)

        verify{liveDataState.onChanged(NowPlayingViewModelStatus.NotFound)}
        coVerify { repository.getAllMoviesNowPlaying() }
    }

    @Test
    fun `GIVEN an error WHEN getting this error THEN assert Error`(){

        //Given
        coEvery { repository.getAllMoviesNowPlaying() } returns NowPlayingStatus.Error(ERROR)

        viewModel = NowPlayingViewModel(repository)
        viewModel.status.observeForever(liveDataState)

        //When
        viewModel.getAllMoviesNowPlaying()

        //Then
        assertEquals(NowPlayingViewModelStatus.Error(ERROR), viewModel.status.value)

        verify { liveDataState.onChanged(NowPlayingViewModelStatus.Error(ERROR)) }
        coVerify { repository.getAllMoviesNowPlaying() }
    }

    companion object {

        val LIST_NOW_PLAYING = Results(results = listOf(
            MovieModel(1, 1.0, "dfgs", "erfger", 2.2, "4t5432", "retgewr", "erte", "sdafwe", emptyList())))

        val NOW_PLAYING_VIEW_MODEL_STATUS = NowPlayingViewModelStatus.Success(LIST_NOW_PLAYING)

        val ERROR: Throwable = Exception("Hi There!")
    }
}





//@RunWith(MockitoJUnitRunner::class)
//class NowPlayingViewModelTest{
//
//    val rule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var nowPlayingRepository: NowPlayingRepository
//
//    private lateinit var viewModel: NowPlayingViewModel
//
//    @Before
//    fun setUp() {
//
//        viewModel = NowPlayingViewModel(nowPlayingRepository)
//    }
//
//    @Test
//    fun testSuccessViewModel(){
//
//        val status: LiveData<NowPlayingStatus> = LiveData<NowPlayingStatus>(NowPlayingStatus.Success(listNowPlaying = Results(results = listOf(
//            MovieModel(
//                1,
//                "124432",
//                "dfgs",
//                "erfger",
//                2.2,
//                "4t5432",
//                "retgewr",
//                "erte",
//                "rwefgeqrw",
//                emptyList()
//            )
//        ))))
//        whenever(nowPlayingRepository.getAllMoviesNowPlaying()).thenReturn(status)
//
//        viewModel.getAllMoviesNowPlaying()
//    }
//}
