package tech.developingdeveloper.movieapi.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import tech.developingdeveloper.movieapi.entity.Movie

@Repository
interface IMovieRepository: CrudRepository<Movie, Int> {

    @Query("SELECT m FROM Movie as m")
    fun getAllMovies(): List<Movie>
}