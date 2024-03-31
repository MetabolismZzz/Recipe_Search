package com.college.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.college.final_project.RecipeSearchActivity;
import com.college.converter.R;
import com.college.converter.databinding.ActivityRecipeDetailsBinding;
import com.squareup.picasso.Picasso;

public class RecipeDetailsActivity extends AppCompatActivity {
    ActivityRecipeDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve recipe details from Intent extras
        String title = getIntent().getStringExtra("title");
        String imageUrl = getIntent().getStringExtra("image_url");
        String summary = getIntent().getStringExtra("summary");
        String sourceUrl = getIntent().getStringExtra("source_url");

        // Update the UI with the details
        binding.recipeTitle.setText(title);
        binding.recipeSummary.setText(summary);
        binding.recipeSourceUrl.setText(sourceUrl);
        Picasso.get().load(imageUrl).into(binding.recipeImage);

        // Set bottom navigation actions
        binding.bottomNavigation.setOnItemSelectedListener(item -> navigateTo(item.getItemId()));
    }

    private boolean navigateTo(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.home_id:
                intent = new Intent(getApplicationContext(), RecipeSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.first_id:
                // intent = new Intent(getApplicationContext(), FirstActivity.class);
                // startActivity(intent);
                break;
            case R.id.second_id:
                // intent = new Intent(getApplicationContext(), SecondActivity.class);
                // startActivity(intent);
                break;
            // Add more cases for other menu items
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.help) {
            // Show help information
            Toast.makeText(this, getString(R.string.help_info), Toast.LENGTH_LONG).show();
            return true;
        } else if (item.getItemId() == R.id.home) {
            // Navigate to the home activity
            startActivity(new Intent(getApplicationContext(), RecipeSearchActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
