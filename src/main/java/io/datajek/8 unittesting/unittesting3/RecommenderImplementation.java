package io.datajek.unittesting3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecommenderImplementation {
    @Autowired
    private Filter filter;

    public RecommenderImplementation(Filter filter) {
        super();
        this.filter = filter;
    }

    /**
     * Method to use a filter to find recommendations
     * @param movie
     * @return
     */
    public String [] recommendMovies (String movie) {
        String[] results = filter.getRecommendations(movie);
        return results;
    }
}
