package com.college.final_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.college.converter.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeSearchActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "RecipePrefs";
    private static final String SEARCH_TERM_KEY = "recipeSearchTerm";

    EditText searchEditText;
    Button searchButton;
    RecyclerView recipesRecyclerView;
    List<Recipe> recipeList = new ArrayList<>();
    RecipeAdapter adapter; // Assume you have a RecipeAdapter similar to SongAdapter in DeezerActivity
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_search); // Ensure this is the correct layout file.

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        recipesRecyclerView = findViewById(R.id.recipesRecyclerView);

        // Restore the search term from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        searchEditText.setText(prefs.getString(SEARCH_TERM_KEY, ""));

        // Setup RecyclerView
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecipeAdapter(this, recipeList);
        recipesRecyclerView.setAdapter(adapter);

        queue = Volley.newRequestQueue(this);

        searchButton.setOnClickListener(view -> {
            String query = searchEditText.getText().toString().trim();
            if (!query.isEmpty()) {
                searchRecipes(query);
                // Save the search term
                prefs.edit().putString(SEARCH_TERM_KEY, query).apply();
            } else {
                Toast.makeText(RecipeSearchActivity.this, R.string.enter_search_term, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchRecipes(String query) {
        String apiKey = "your_api_key"; // Replace with your actual API key
        String url = "https://api.spoonacular.com/recipes/complexSearch?query=" + query + "&apiKey=" + apiKey;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("results");
                        recipeList.clear(); // Clear the list before adding new data

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject recipeObject = jsonArray.getJSONObject(i);
                            Recipe recipe = new Recipe();
                            recipe.setTitle(recipeObject.getString("title"));
                            recipe.setImageUrl(recipeObject.getString("image"));
                            // Assume Recipe class has a constructor that accepts these parameters
                            recipeList.add(recipe);
                        }

                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(RecipeSearchActivity.this, R.string.error_occurred, Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(RecipeSearchActivity.this, R.string.error_fetch_data, Toast.LENGTH_SHORT).show()
        );

        queue.add(stringRequest);
    }
}
