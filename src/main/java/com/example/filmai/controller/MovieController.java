package com.example.filmai.controller;

import com.example.filmai.model.Movie;
import com.example.filmai.model.MovieDAO;
import com.example.filmai.utils.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MovieController {
    // Formos elementai:
    @FXML
    private TextField movieID;
    @FXML
    private TextField movieName;
    @FXML
    private TextField movieDuration;
    // CheckBox:
    @FXML
    private CheckBox actorNum1;
    @FXML
    private CheckBox actorNum2;
    @FXML
    private CheckBox actorNum3;
    // RadioButton:
    @FXML
    private ToggleGroup directors;
    @FXML
    private RadioButton directorNum1;
    @FXML
    private RadioButton directorNum2;
    @FXML
    private RadioButton directorNum3;
    @FXML
    private Label status;
    @FXML
    private ComboBox<Integer> releaseDate;
    // Mygtukai:
    @FXML
    private Button createButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    // Lenteles stulpeliai:
    @FXML
    private TableView<Movie> movieTable;
    @FXML
    //TableColumn<S, T> S represents the type of TableView items (the model type (Movie))
    //T represents the type of the column data. In this case Integer.
    private TableColumn<Movie, Integer> movieIDColumn;
    @FXML
    private TableColumn<Movie, String> movieNameColumn;
    @FXML
    private TableColumn<Movie, Integer> durationColumn;
    @FXML
    private TableColumn<Movie, String> actorsColumn;
    @FXML
    private TableColumn<Movie, String> directorColumn;
    @FXML
    private TableColumn<Movie, Integer> releaseDateColumn;


    ObservableList<Integer> list = FXCollections.observableArrayList(
            1999, 1974, 1989, 1979, 1997, 1982, 1994
    );

    ObservableList<Movie> observableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        releaseDate.setItems(list);

        Tooltip movieIDTooltip = new Tooltip();
        movieIDTooltip.setText("Must contain only numbers");
        Tooltip.install(movieID, movieIDTooltip);
    }

    @FXML
    public void onSearchButtonClick() {
        observableList.clear();
        String nameOfMovie = movieName.getText();

        ArrayList<Movie> movieList = MovieDAO.searchByName(nameOfMovie);
        for (Movie movie : movieList) {
            observableList.add(new Movie(movie.getMovieID(), movie.getMovieTitle(), movie.getMovieDuration(),
                    movie.getActors(), movie.getDirectors(), movie.getReleaseYear()));

            //PropertyValueFactory<>() takes its parameters from Movie class not from database!!!
            movieIDColumn.setCellValueFactory(new PropertyValueFactory<>("movieID"));
            movieNameColumn.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
            durationColumn.setCellValueFactory(new PropertyValueFactory<>("movieDuration"));
            actorsColumn.setCellValueFactory(new PropertyValueFactory<>("actors"));
            directorColumn.setCellValueFactory(new PropertyValueFactory<>("directors"));
            releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

            movieTable.setItems(observableList);
        }
        if (movieList.isEmpty()) {
            status.setText("Unsuccessful search attempt");
        } else {
            status.setText("Successfully found movie by its name");
        }
    }

    @FXML
    public void onCreateButtonClick() {
        String nameOfMovie = movieName.getText();
        String durationOfMovie = movieDuration.getText();
        // CheckBox:
        String actors = "";
        if (actorNum1.isSelected()) {
            actors += actorNum1.getText() + ",";
        }
        if (actorNum2.isSelected()) {
            actors += actorNum2.getText() + ",";
        }
        if (actorNum3.isSelected()) {
            actors += actorNum3.getText() + ",";
        }
        // RadioBox:
        String director = "";
        if (directorNum1.isSelected()) {
            director = directorNum1.getText();
        } else if (directorNum2.isSelected()) {
            director = directorNum2.getText();
        } else if (directorNum3.isSelected()) {
            director = directorNum3.getText();
        }
        // ComboBox
        String year = "";
        if (!releaseDate.getSelectionModel().isEmpty()) {
            year = releaseDate.getSelectionModel().getSelectedItem().toString();
        }
        //TODO reikes pravaliduoti filmo pavadinima ir trukme (sukurti validacijos taisykles)
        // if (patikrinti filmo pavadinima, kad nebutu tuscias)
        // else if (patikrinti trukme, kad butu tik skaiciai)
        // else if (aktorius tikrinsime)
        if (!Validation.isValidMovieName(nameOfMovie)) {
            status.setText("Enter a movie name");
        } else if (!Validation.isValidMovieDuration(durationOfMovie)) {
            status.setText("Enter movie duration");
        } else if (actors.isEmpty()) {
            status.setText("Pick an actor or actors");
        } else if (director.isEmpty()) {
            status.setText("Pick a director");
        } else if (year.isEmpty()) {
            status.setText("Pick a release date");
        } else {
            int duration = Integer.parseInt(durationOfMovie);
            int movieYear = Integer.parseInt(year);
            Movie movie = new Movie(nameOfMovie, duration, actors, director, movieYear);
            MovieDAO.create(movie);
            status.setText("Successfully created movie inside the database");
        }
        //int year2 = Integer.parseInt(year);
        // else (visais kitais atvejais, galime registruoti irasa DB, nes jis praejo validacija
    }

    @FXML
    public void onUpdateButtonClick() {
        String nameOfMovie = movieName.getText();
        String durationOfMovie = movieDuration.getText();
        String idOfMovie = movieID.getText();
        // CheckBox:
        String actors = "";
        if (actorNum1.isSelected()) {
            actors += actorNum1.getText() + ",";
        }
        if (actorNum2.isSelected()) {
            actors += actorNum2.getText() + ",";
        }
        if (actorNum3.isSelected()) {
            actors += actorNum3.getText() + ",";
        }
        // RadioBox:
        String director = "";
        if (directorNum1.isSelected()) {
            director = directorNum1.getText();
        } else if (directorNum2.isSelected()) {
            director = directorNum2.getText();
        } else if (directorNum3.isSelected()) {
            director = directorNum3.getText();
        }
        // ComboBox
        String year = "";
        if (!releaseDate.getSelectionModel().isEmpty()) {
            year = releaseDate.getSelectionModel().getSelectedItem().toString();
        }

        if (!Validation.isValidMovieID(idOfMovie)) {
            status.setText("Enter movie database ID");
        } else if (!Validation.isValidMovieName(nameOfMovie)) {
            status.setText("Enter a movie name");
        } else if (!Validation.isValidMovieDuration(durationOfMovie)) {
            status.setText("Enter movie duration");
        } else if (actors.isEmpty()) {
            status.setText("Pick an actor or actors");
        } else if (director.isEmpty()) {
            status.setText("Pick a director");
        } else if (year.isEmpty()) {
            status.setText("Pick a release date");
        } else {
            int duration = Integer.parseInt(durationOfMovie);
            int movieYear = Integer.parseInt(year);
            int idMovie = Integer.parseInt(idOfMovie);
            Movie movie = new Movie(idMovie, nameOfMovie, duration, actors, director, movieYear);
            MovieDAO.update(movie);
            status.setText("Successfully updated movie in the database");
        }
    }

    @FXML
    public void onDeleteButtonClick() {
        String idOfMovie = movieID.getText();
        if (!Validation.isValidMovieID(idOfMovie)) {
            status.setText("Enter movie database ID");
        } else {
            int idMovie = Integer.parseInt(idOfMovie);
            MovieDAO.delete(idMovie);
            status.setText("Successfully deleted item from the database");
        }
    }
}
