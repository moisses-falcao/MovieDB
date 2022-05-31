package com.example.citmoviedatabase_mf.nowplaying

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.citmoviedatabase_mf.models.MovieModel
import com.example.citmoviedatabase_mf.models.Results
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingRepository
import com.example.citmoviedatabase_mf.repository.nowplaying.NowPlayingStatus
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class NowPlayingViewModelTes{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: NowPlayingViewModel

    @Test
    fun `GIVEN a fake movie list WHEN getting movie list THEN assert Success`(){

        //Given
        val repositorySuccess = MockRepository(NowPlayingStatus.Success(LIST_NOW_PLAYING))
        viewModel = NowPlayingViewModel(repositorySuccess)

        //When
        viewModel.getAllMoviesNowPlaying()

        //Then
        assertEquals(NOW_PLAYING_VIEW_MODEL_STATUS, viewModel.status.value)
    }

    @Test
    fun `GIVEN a NotFound State WHEN getting this THEN assert NotFound`(){

        //Given
        val repositoryNotFound = MockRepository(NowPlayingStatus.NotFound)
        viewModel = NowPlayingViewModel(repositoryNotFound)

        //When
        viewModel.getAllMoviesNowPlaying()

        //Then
        assertEquals(NowPlayingViewModelStatus.NotFound, viewModel.status.value)
    }

    @Test
    fun `GIVEN an error WHEN getting this error THEN assert Error`(){

        //Given
        val repositoryError = MockRepository(NowPlayingStatus.Error(ERROR))
        viewModel = NowPlayingViewModel(repositoryError)

        //When
        viewModel.getAllMoviesNowPlaying()

        //Then
        assertEquals(NowPlayingViewModelStatus.Error(ERROR), viewModel.status.value)

    }

    companion object {

        val LIST_NOW_PLAYING = Results(results = listOf(
            MovieModel(1, 1.0, "dfgs", "erfger", 2.2, "4t5432", "retgewr", "erte", "sdafwe", emptyList())))

        val NOW_PLAYING_VIEW_MODEL_STATUS = NowPlayingViewModelStatus.Success(LIST_NOW_PLAYING)

        val ERROR: Throwable = Exception("Hi There!")
    }
}

class MockRepository(private val results: NowPlayingStatus): NowPlayingRepository{
    override fun getAllMoviesNowPlaying(nowPlayingStatus: (NowPlayingStatus) -> Unit) {
        nowPlayingStatus(results)
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
