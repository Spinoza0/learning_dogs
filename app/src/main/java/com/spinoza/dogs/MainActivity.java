package com.spinoza.dogs;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewDog;
    private ProgressBar progressBar;
    private Button buttonLoadImage;

    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        loadDogImage();

        buttonLoadImage.setOnClickListener(view -> loadDogImage());
    }

    private void initViews() {
        imageViewDog = findViewById(R.id.imageViewDog);
        progressBar = findViewById(R.id.progressBar);
        buttonLoadImage = findViewById(R.id.buttonLoadImage);
    }

    private void loadDogImage() {
        viewModel.getIsLoading().observe(this, isLoading ->
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE)
        );
        viewModel.loadDogImage();
        viewModel.getDogImage().observe(this, dogImage -> Glide.with(MainActivity.this)
                .load(dogImage.getMessage())
                .into(imageViewDog));

        viewModel.getIsError().observe(this, isError -> {
            if (isError) {
                Toast.makeText(
                        MainActivity.this,
                        R.string.image_loading_error,
                        Toast.LENGTH_SHORT
                ).show();
                viewModel.setIsError(false);
            }
        });
    }

}