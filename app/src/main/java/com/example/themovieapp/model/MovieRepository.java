package com.example.themovieapp.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.TheMovieDB.R;
import com.example.themovieapp.serviceapi.MovieApiService;
import com.example.themovieapp.serviceapi.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static final String TAG = "MovieRepository";

    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {
        MovieApiService movieApiService = RetrofitInstance.getService();
        Call<Result> call = movieApiService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Result result = response.body();
                    if (result != null && result.getResults() != null) {
                        List<Movie> movieList = result.getResults();
                        mutableLiveData.setValue(new ArrayList<>(movieList));
                    } else {
                        Log.e(TAG, "Response body is null or result is empty");
                    }
                } else {
                    Log.e(TAG, "Response is not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {
                Log.e(TAG, "Failed to fetch movies", throwable);
            }
        });
        return mutableLiveData;
    }
}