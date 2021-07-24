package tech.developingdeveloper.movieapi.service

import org.springframework.stereotype.Service
import tech.developingdeveloper.movieapi.dto.MovieDTO
import tech.developingdeveloper.movieapi.repository.IMovieRepository
import tech.developingdeveloper.movieapi.utils.exception.MovieException
import tech.developingdeveloper.movieapi.utils.mapper.MovieMapper

@Service
class MovieService(
    private val movieRepository: IMovieRepository,
    private val movieMapper: MovieMapper
) : IMovieService {
    override fun getMovies(): List<MovieDTO> {
        val movies = movieRepository.getAllMovies()

        if(movies.isEmpty())
            throw MovieException("List of movies is empty")

        return movies.map {
            movieMapper.fromEntity(it)
        }
    }

    override fun getMovie(id: Int): MovieDTO {
        val optionalMovie = movieRepository.findById(id)
        val movie = optionalMovie.orElseThrow{ MovieException("Movie with id $id is not present.") }
        return movieMapper.fromEntity(movie)
    }

    override fun createMovie(movieDTO: MovieDTO): MovieDTO {
        if(movieDTO.id == -1) {
            throw IllegalArgumentException("Id must be not null or equal -1.")
        }

        // rating, name, noID
        val movie = movieMapper.toEntity(movieDTO)
        // it will have an Id
        movieRepository.save(movie)
        return movieMapper.fromEntity(movie)
    }

    override fun updateMovie(movieDTO: MovieDTO): MovieDTO {
        val checkExistingMovie = movieRepository.existsById(movieDTO.id)
        if(!checkExistingMovie) {
            throw MovieException("Movie with id ${movieDTO.id} is not present.")
        }

        if(movieDTO.rating == 0.0 || movieDTO.name == "") {
            throw MovieException("Complete movie object is expected.")
        }

        val movie = movieMapper.toEntity(movieDTO)
        movieRepository.save(movie)
        return movieMapper.fromEntity(movie)
    }

    override fun deleteMovie(id: Int) {
        val checkExistingMovie = movieRepository.existsById(id)
        if(!checkExistingMovie) {
            throw MovieException("Movie with id $id is not present")
        }

        movieRepository.deleteById(id)
    }
}