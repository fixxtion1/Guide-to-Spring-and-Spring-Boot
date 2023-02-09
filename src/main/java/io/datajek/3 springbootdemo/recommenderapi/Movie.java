package io.datajek.springbootdemo.recommenderapi;

public class Movie {
    int id;
    String name;
    double rating;

    /**
     * Constructor with no params
     */
    public Movie() {
    }

    /**
     * Constructor with params
     * @param id
     * @param name
     * @param rating
     */
    public Movie(int id, String name, double rating) {
        super();
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    /**
     * Id getter method
     * @return id as an integer
     */
    public int getId() {
        return id;
    }

    /**
     * Name getter method
     * @return name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Rating getter method
     * @return rating as a double
     */
    public double getRating() {
        return rating;
    }

    /**
     * Format the movie with id, name, and rating
     * @return movie info as a formatted string
     */
    @Override
    public String toString() {
        return "Movie [id=" + id + ", name=" + name + ", rating=" + rating + "]";
    }
}
