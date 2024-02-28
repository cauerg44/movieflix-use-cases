package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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
    private AuthService authService;

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {

        User user = authService.authenticated();

        try {
            Review review = new Review();
            review.setMovie(movieRepository.getReferenceById(dto.getMovieId()));
            review.setUser(user);
            review.setText(dto.getText());

            repository.save(review);

            return new ReviewDTO(review);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found " + dto.getMovieId());
        }
    }
}
