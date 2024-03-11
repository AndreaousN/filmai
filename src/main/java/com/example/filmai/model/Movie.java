package com.example.filmai.model;

public class Movie {
    private int movieID;
    private String movieTitle;
    private int movieDuration;
    private String actors;
    private String directors;
    private int releaseYear;
    private int userID;

    public Movie(int movieID, String movieTitle, int movieDuration, String actors,
                 String directors, int releaseYear) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.movieDuration = movieDuration;
        this.actors = actors;
        this.directors = directors;
        this.releaseYear = releaseYear;
    }

    public Movie(int movieID, String movieTitle, int movieDuration, String actors, String directors, int releaseYear, int userID) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.movieDuration = movieDuration;
        this.actors = actors;
        this.directors = directors;
        this.releaseYear = releaseYear;
        this.userID = userID;
    }

    public Movie(String movieTitle, int movieDuration, String actors, String directors, int releaseYear, int userID) {
        this.movieTitle = movieTitle;
        this.movieDuration = movieDuration;
        this.actors = actors;
        this.directors = directors;
        this.releaseYear = releaseYear;
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + movieID +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieDuration=" + movieDuration +
                ", actors='" + actors + '\'' +
                ", directors='" + directors + '\'' +
                ", releaseYear=" + releaseYear +
                ", userID=" + userID +
                '}';
    }
}
