package tech.developingdeveloper.movieapi.utils.mapper

import org.springframework.stereotype.Service
import tech.developingdeveloper.movieapi.dto.MovieDTO
import tech.developingdeveloper.movieapi.entity.Movie

@Service
class MovieMapper: Mapper<MovieDTO, Movie> {

    override fun fromEntity(entity: Movie): MovieDTO = MovieDTO(
        id = entity.id,
        name = entity.name,
        rating = entity.rating
    )

    override fun toEntity(domain: MovieDTO): Movie = Movie(
        id = domain.id,
        name = domain.name,
        rating = domain.rating
    )
}