package com.example.citmoviedatabase_mf.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.citmoviedatabase_mf.models.*
import com.example.citmoviedatabase_mf.repository.details.DetailsRepository
import com.example.citmoviedatabase_mf.repository.details.DetailsStatus
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class DetailsViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailsViewModel


    //Details
    @Test
    fun `GIVEN a fake movie details WHEN getting this fake movie details THEN assert Success`(){

        //Given
        val repositorySuccess = MockRepository(DetailsStatus.SuccessDetails(MOVIE_DETAILS))
        viewModel = DetailsViewModel(repositorySuccess)

        //WHEN
        viewModel.getMovieDetails(MOVIE_ID)

        //THEN
        assertEquals(MOVIE_DETAILS_STATUS, viewModel.statusDetails.value)
    }

    @Test
    fun `GIVEN a NotFound list WHEN getting this THEN assert NotFound`(){

        //Given
        val repositoryNotFound = MockRepository(DetailsStatus.NotFound)
        viewModel = DetailsViewModel(repositoryNotFound)

        //When
        viewModel.getMovieDetails(MOVIE_ID)

        //Then
        assertEquals(DetailsViewModelStatus.NotFound, viewModel.statusDetails.value)
    }

    @Test
    fun `GIVEN an error WHEN getting this error THEN assert Error`(){

        //Given
        val repositoryError = MockRepository(DetailsStatus.Error(ERROR))
        viewModel = DetailsViewModel(repositoryError)

        //When
        viewModel.getMovieDetails(MOVIE_ID)

        //Then
        assertEquals(DetailsViewModelStatus.Error(ERROR), viewModel.statusDetails.value)
    }

    //Credits
    @Test
    fun `GIVEN a fake movie casting WHEN getting this fake casting THEN assert Success`(){

        //Given
        val repositorySuccess = MockRepository(DetailsStatus.SuccessCredits(MOVIE_CREDITS))
        viewModel = DetailsViewModel(repositorySuccess)

        //WHEN
        viewModel.getMovieCredits(MOVIE_ID)

        //THEN
        assertEquals(MOVIE_CASTING_STATUS, viewModel.statusCredits.value)
    }

    @Test
    fun `GIVEN a NotFound casting list WHEN getting this list THEN assert NotFound`(){

        //Given
        val repositoryNotFound = MockRepository(DetailsStatus.NotFound)
        viewModel = DetailsViewModel(repositoryNotFound)

        //When
        viewModel.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(DetailsViewModelStatus.NotFound, viewModel.statusCredits.value)
    }

    @Test
    fun `GIVEN a casting error WHEN getting this error THEN assert Error`(){

        //Given
        val repositoryError = MockRepository(DetailsStatus.Error(ERROR))
        viewModel = DetailsViewModel(repositoryError)

        //When
        viewModel.getMovieCredits(MOVIE_ID)

        //Then
        assertEquals(DetailsViewModelStatus.Error(ERROR), viewModel.statusCredits.value)
    }

    //Scenes
    @Test
    fun `GIVEN a fake movie scenes WHEN getting this fake scenes THEN assert Success`(){

        //Given
        val repositorySuccess = MockRepository(DetailsStatus.SuccessScenes(MOVIE_SCENES))
        viewModel = DetailsViewModel(repositorySuccess)

        //WHEN
        viewModel.getMovieScenes(MOVIE_ID)

        //THEN
        assertEquals(MOVIE_SCENES_STATUS, viewModel.statusScenes.value)
    }

    @Test
    fun `GIVEN a NotFound scenes list WHEN getting this list THEN assert NotFound`(){

        //Given
        val repositoryNotFound = MockRepository(DetailsStatus.NotFound)
        viewModel = DetailsViewModel(repositoryNotFound)

        //When
        viewModel.getMovieScenes(MOVIE_ID)

        //Then
        assertEquals(DetailsViewModelStatus.NotFound, viewModel.statusScenes.value)
    }

    @Test
    fun `GIVEN a scenes error WHEN getting this error THEN assert Error`(){

        //Given
        val repositoryError = MockRepository(DetailsStatus.Error(ERROR))
        viewModel = DetailsViewModel(repositoryError)

        //When
        viewModel.getMovieScenes(MOVIE_ID)

        //Then
        assertEquals(DetailsViewModelStatus.Error(ERROR), viewModel.statusScenes.value)
    }

    companion object{
        val MOVIE_DETAILS: MovieDetailsModel = MovieDetailsModel(1, "dsfwe", "sdfds", 2.0, "sdfsd", emptyList(), 100)

        val MOVIE_DETAILS_STATUS = DetailsViewModelStatus.SuccessDetails(MOVIE_DETAILS)

        const val MOVIE_ID = "1"

        val MOVIE_CREDITS = CastModel(listOf(CastAndCrewModel(1, "dfz", "ars", "sdf")))

        val MOVIE_CASTING_STATUS = DetailsViewModelStatus.SuccessCredits(MOVIE_CREDITS)

        val MOVIE_SCENES = SceneModel(listOf(PhotoModel(1, "spqwq")))

        val MOVIE_SCENES_STATUS = DetailsViewModelStatus.SuccessScenes(MOVIE_SCENES)

        val ERROR: Throwable = Exception("Hi there!")
    }
}

class MockRepository(private val results: DetailsStatus): DetailsRepository{
    override fun getMovieDetails(movieId: String, detailsStatus: (DetailsStatus) -> Unit) {
        detailsStatus(results)
    }

    override fun getMovieCredits(movieId: String, detailsStatus: (DetailsStatus) -> Unit) {
        detailsStatus(results)
    }

    override fun getMovieScenes(movieId: String, detailsStatus: (DetailsStatus) -> Unit) {
        detailsStatus(results)
    }
}