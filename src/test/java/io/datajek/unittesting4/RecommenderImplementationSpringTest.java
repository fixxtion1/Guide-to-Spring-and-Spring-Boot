package io.datajek.unittesting4;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=MovieRecommenderSystemApplication.class)
class RecommenderImplementationSpringTest {

    @Autowired
    private RecommenderImplementation recommenderImpl;

    @Test
    public void testRecommendMovies() {
        assertArrayEquals(new String[] {"Finding Nemo", "Ice Age", "Toy Story"}, recommenderImpl.recommendMovies("Finding Dory"));
    }

}