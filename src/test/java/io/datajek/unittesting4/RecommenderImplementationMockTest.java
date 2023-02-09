package io.datajek.unittesting4;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecommenderImplementationMockTest {

    @InjectMocks
    private RecommenderImplementation recommenderImpl;

    @Mock
    private Filter mockFilter;

    @Test
    void testRecommendMovies_noRecommendationsFound() {
        when(mockFilter.getRecommendations("Finding Dory")).thenReturn(new String[] {});
        assertArrayEquals(new String[] {}, recommenderImpl.recommendMovies("Finding Dory"));
    }
}