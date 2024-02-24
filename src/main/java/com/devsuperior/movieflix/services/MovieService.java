package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> findAllPaged(Pageable pageable) {
        Page<Movie> list = repository.findAll(pageable);
        return list.map(x -> new MovieCardDTO(x));
    }

    @Transactional(readOnly = true)
    public Page<MovieDetailsDTO> findByGenreId(Long genreId, Pageable pageable) {
        Page<Movie> list = repository.findByGenreId(genreId, pageable);
        return list.map(x -> new MovieDetailsDTO(x));
    }
}
