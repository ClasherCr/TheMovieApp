package com.example.themovieapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.themovieapp.databinding.ActivityMainBinding;
import com.example.themovieapp.model.Movie;
import com.example.themovieapp.view.MovieAdapter;
import com.example.themovieapp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Set up insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.swipe_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView and Adapter
        recyclerView = binding.recyclerview;
        movieAdapter = new MovieAdapter(this, movies);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Set up SwipeRefreshLayout
        swipeRefreshLayout = binding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.black);
        swipeRefreshLayout.setOnRefreshListener(() -> getPopularMovies());

        // Load movies initially
        getPopularMovies();
    }

    private void getPopularMovies() {
        viewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                if (moviesFromLiveData != null) {
                    movies.clear(); // Clear existing data
                    movies.addAll(moviesFromLiveData); // Add new data
                    movieAdapter.notifyDataSetChanged();
                }
                swipeRefreshLayout.setRefreshing(false); // Stop the refreshing animation
            }
        });
    }
}