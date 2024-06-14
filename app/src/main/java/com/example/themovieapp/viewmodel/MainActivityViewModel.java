package com.example.themovieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.themovieapp.model.Movie;
import com.example.themovieapp.model.MovieRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

    // Live Data
    public MutableLiveData<ArrayList<Movie>> getAllMovies(){
        return repository.getMutableLiveData();
    }

}
