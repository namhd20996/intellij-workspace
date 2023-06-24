package com.pokemonreview.api.repository;

import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.models.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReviewRepositoryTests {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewRepositoryTests(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Test
    public void reviewRepository_SaveAll_ReturnSaveReview() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        Review saveReview = reviewRepository.save(review);

        Assertions.assertThat(saveReview).isNotNull();
        Assertions.assertThat(saveReview.getId()).isGreaterThan(0);
    }
}
