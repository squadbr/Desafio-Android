package br.com.cemobile.moviescoreseeker

import android.arch.lifecycle.Observer
import br.com.cemobile.moviescoreseeker.data.repository.MoviesRepository
import br.com.cemobile.moviescoreseeker.interactor.usecase.movies.SearchMovies
import br.com.cemobile.moviescoreseeker.interactor.usecase.movies.SearchMoviesImpl
import br.com.cemobile.moviescoreseeker.model.Movie
import br.com.cemobile.moviescoreseeker.model.Resource
import br.com.cemobile.moviescoreseeker.model.Status
import br.com.cemobile.moviescoreseeker.search.SearchMoviesViewModel
import kotlinx.coroutines.experimental.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchMoviesViewModelUnitTest : BaseTest(), KoinTest {

    private val repository by inject<MoviesRepository>()
    @Mock private lateinit var movieListObserver: Observer<Resource<List<Movie>>>
    private lateinit var useCase: SearchMovies
    private lateinit var viewModel: SearchMoviesViewModel

    @Before
    override fun `before each test`() {
        super.`before each test`()
        MockitoAnnotations.initMocks(this)
        useCase = spy(SearchMoviesImpl(repository))
        viewModel = spy(SearchMoviesViewModel(useCase)).apply {
            enableUnitTest()
            moviesLiveData.observeForever(movieListObserver)
        }
    }

    @Test
    fun `should not search when text lenght is lower than two characters`() {
        verify(viewModel).searchMovies("ba")
        assert(viewModel.moviesLiveData.value != null)
        assert(viewModel.moviesLiveData.value!!.data == null)
        assert(viewModel.moviesLiveData.value!!.status == Status.NONE)
    }

    @Test
    fun `should request all iron movies`() = runBlocking {
        server.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(readJsonFromFile("movies_list.json")))

        verify(viewModel).searchMovies("iron")
        await()
        assert(viewModel.moviesLiveData.value != null)
        assert(viewModel.moviesLiveData.value!!.data!![0].title == "Iron Man")
    }

}