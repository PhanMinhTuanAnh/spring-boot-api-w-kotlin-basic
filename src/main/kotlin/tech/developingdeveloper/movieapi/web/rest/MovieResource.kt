package tech.developingdeveloper.movieapi.web.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*
import tech.developingdeveloper.movieapi.dto.MovieDTO
import tech.developingdeveloper.movieapi.service.MovieService

@RestController
class MovieResource(
    private val movieService: MovieService
) {

    @GetMapping
    fun getMovies() : ResponseEntity<List<MovieDTO>> = ResponseEntity.ok(movieService.getMovies())

    @GetMapping("/{id}")
    fun getMovies(@PathVariable id: Int) : ResponseEntity<MovieDTO> = ResponseEntity.ok(
        movieService.getMovie(id)
    )

    @PostMapping
    fun createMovie(@RequestBody movieDTO: MovieDTO) : ResponseEntity<MovieDTO> = ResponseEntity(
        movieService.createMovie(movieDTO), HttpStatus.CREATED
    )

    @PutMapping
    fun updateMovie(@RequestBody movieDTO: MovieDTO) : ResponseEntity<MovieDTO> = ResponseEntity(
        movieService.updateMovie(movieDTO), HttpStatus.ACCEPTED
    )

    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id: Int) : ResponseEntity<Unit> = ResponseEntity(
        movieService.deleteMovie(id), HttpStatus.NO_CONTENT
    )
}