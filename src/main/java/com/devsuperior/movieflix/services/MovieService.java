package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public MovieDetailsDTO findById(Long id) {
        Optional<Movie> obj = repository.findById(id);
        Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MovieDetailsDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<MovieDetailsDTO> findByGenreId(Long genreId, Pageable pageable) {
        Page<Movie> list = repository.findByGenreId(genreId, pageable);
        return list.map(x -> new MovieDetailsDTO(x));
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findReviewsByMovieId(Long movieId) {
        Movie movie = repository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found."));
        return movie.getReviews().stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
    }

}
