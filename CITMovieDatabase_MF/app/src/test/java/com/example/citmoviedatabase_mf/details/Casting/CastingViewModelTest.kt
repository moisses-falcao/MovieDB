package com.example.citmoviedatabase_mf.details.Casting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.citmoviedatabase_mf.models.CastAndCrewModel
import com.example.citmoviedatabase_mf.models.CastModel
import com.example.citmoviedatabase_mf.repository.casting.CastingRepository
import com.example.citmoviedatabase_mf.repository.casting.CastingStatus
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CastingViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: CastingViewModel

    @Test
    fun `GIVEN a fake casting list WHEN getting this list THEN assert Success`(){

        //Given
        val repositorySuccess = MockRepository(CastingStatus.Success(CASTING))
        viewModel = CastingViewModel(repositorySuccess)

        //When
        viewModel.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(CastingViewModelStatus.Success(CASTING) , viewModel.status.value)
    }

    @Test
    fun `GIVEN a NotFound state WHEN getting this THEN assert NotFound`(){

        //Given
        val repositorySuccess = MockRepository(CastingStatus.NotFound)
        viewModel = CastingViewModel(repositorySuccess)

        //When
        viewModel.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(CastingViewModelStatus.NotFound , viewModel.status.value)
    }

    @Test
    fun `GIVEN an error state WHEN getting this THEN assert Error`(){

        //Given
        val repositorySuccess = MockRepository(CastingStatus.Error(ERROR))
        viewModel = CastingViewModel(repositorySuccess)

        //When
        viewModel.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(CastingViewModelStatus.Error(ERROR) , viewModel.status.value)
    }

    companion object{
        val MOVIE_ID = "1"

        val CASTING = CastModel(listOf(CastAndCrewModel(1, "a", "b", "c")))

        val ERROR: Throwable = Exception("Hi there!")
    }
}

class MockRepository(private val result: CastingStatus) : CastingRepository{
    override fun getMovieCredits(movieId: String, castingStatus: (CastingStatus) -> Unit) {
        castingStatus(result)
    }
}