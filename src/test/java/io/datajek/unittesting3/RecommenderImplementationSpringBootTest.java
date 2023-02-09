package io.datajek.unittesting3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

//1. Launch context
@SpringBootTest
class RecommenderImplementationSpringBootTest {

    //2. Load bean from context
    @Autowired
    private RecommenderImplementation recommenderImpl;

    @Test
    void testRecommendMovies() {
        //3+4. Call method on the bean & Check if the result is as expected
        assertArrayEquals(new String[] {"Finding Nemo", "Ice Age", "Toy Story"}, recommenderImpl.recommendMovies("Finding Dory"));
    }
}