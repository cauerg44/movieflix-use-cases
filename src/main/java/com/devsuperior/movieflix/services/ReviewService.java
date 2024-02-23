package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review review = new Review();
        review.setText(dto.getText());
        Long movieId = dto.getMovieId();
        if (movieId != null) {
            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new IllegalArgumentException("Movie not found."));
            review.setMovie(movie);
        }
        return new ReviewDTO(review);
    }


}
