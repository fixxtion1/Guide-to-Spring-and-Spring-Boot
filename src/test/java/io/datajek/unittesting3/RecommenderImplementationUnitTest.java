package io.datajek.unittesting3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecommenderImplementationUnitTest {

    @Test
    void testRecommendMovies_withCollaborativeFilter() {
        //1. Initialize the object
        RecommenderImplementation recommenderImpl = new RecommenderImplementation(new CollaborativeFilter());
        //2. Call method on the bean
        String[] actualResult = recommenderImpl.recommendMovies("Finding Dory");
        //3. Check if the result is as expected
        assertArrayEquals(new String[] {"Finding Nemo", "Ice Age", "Toy Story"}, actualResult);
    }

    @Test
    void testRecommendMovies_withContentBasedFilter() {
        //1. Initialize the object
        RecommenderImplementation recommenderImpl = new RecommenderImplementation(new ContentBasedFilter());
        //2+3. Call method on the bean & Check if the result is as expected
        assertArrayEquals(new String[] {"Happy Feet", "Ice Age", "Shark Tale"}, recommenderImpl.recommendMovies("Finding Dory"));
    }

}