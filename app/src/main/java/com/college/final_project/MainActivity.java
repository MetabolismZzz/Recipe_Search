package com.college.final_project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.college.converter.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText searchEditText;
    Button searchButton;
    RecyclerView recipesRecyclerView;
    List<Recipe> recipeList = new ArrayList<>();
    Recipe_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure this is the correct layout file.

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        recipesRecyclerView = findViewById(R.id.recipesRecyclerView);

        // Setup RecyclerView
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Recipe_Adapter(this, recipeList);
        recipesRecyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(view -> {
            String query = searchEditText.getText().toString().trim();
            if (!query.isEmpty()) {
                searchRecipes(query);
            } else {
                Toast.makeText(MainActivity.this, R.string.enter_search_term, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchRecipes(String query) {
        String url = "https://api.spoonacular.com/recipes/complexSearch?query=" + query + "&apiKey=90c0e8c3cef743a8b0e2c98d7401ec6e";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            recipeList.clear(); // Clear the list before adding new data

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject recipeObject = jsonArray.getJSONObject(i);
                                Recipe recipe = new Recipe();
                                recipe.setTitle(recipeObject.getString("title"));
                                recipe.setImageUrl(recipeObject.getString("image"));
                                recipeList.add(recipe);
                            }

                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, R.string.error_occurred, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, R.string.error_fetch_data, Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}