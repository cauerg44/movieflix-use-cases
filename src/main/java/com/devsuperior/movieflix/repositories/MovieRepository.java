package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT new com.devsuperior.movieflix.dto.MovieCardDTO(mc.id, mc.title, mc.subTitle, mc.year, mc.imgUrl) FROM Movie mc WHERE :genreId IS NULL OR mc.genre.id = :genreId ORDER BY mc.title")
    Page<MovieCardDTO> findByGenreId(Long genreId, Pageable pageable);
}
