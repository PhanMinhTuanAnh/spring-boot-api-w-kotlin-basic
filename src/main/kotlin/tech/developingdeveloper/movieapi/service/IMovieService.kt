package tech.developingdeveloper.movieapi.service

import org.springframework.stereotype.Service
import tech.developingdeveloper.movieapi.dto.MovieDTO

@Service
interface IMovieService {

    fun getMovies(): List<MovieDTO>

    fun getMovie(id: Int): MovieDTO

    fun createMovie(movieDTO: MovieDTO): MovieDTO

    fun updateMovie(movieDTO: MovieDTO): MovieDTO

    fun deleteMovie(id: Int)
}