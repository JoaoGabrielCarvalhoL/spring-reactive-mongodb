package br.com.carv.netflux.domain;

import java.util.Date;

public class MovieEvent {

    private String movieId;
    private Date movieDate;

    public MovieEvent() {

    }

    public MovieEvent(String movieId, Date movieDate) {
        this.movieId = movieId;
        this.movieDate = movieDate;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Date getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(Date movieDate) {
        this.movieDate = movieDate;
    }
}
