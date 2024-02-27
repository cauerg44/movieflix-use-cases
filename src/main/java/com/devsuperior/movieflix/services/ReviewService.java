package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review review = new Review();
        review.setText(dto.getText());

        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found."));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        review.setMovie(movie);
        review.setUser(user);

        movieRepository.save(movie);
        userRepository.save(user);

        return new ReviewDTO(repository.save(review));
    }
}
