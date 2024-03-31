package com.college.final_project;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.college.final_project.databinding.ActivityFavoriteBinding;
import com.college.final_project.databinding.ViewRecipeBinding;
import com.google.android.material.snackbar.Snackbar;

import com.college.converter.R;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private ActivityFavoriteBinding binding;
    private RecipeViewModel recipeModel;
    private RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        recipeModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        // Initialize the RecyclerView
        binding.recycle2.setLayoutManager(new LinearLayoutManager(this));
        recipeModel.getAllRecipes().observe(this, recipes -> {
            myAdapter = new RecipeAdapter(recipes);
            binding.recycle2.setAdapter(myAdapter);
        });
    }

    private class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

        private final List<Recipe> recipes;

        RecipeAdapter(List<Recipe> recipes) {
            this.recipes = recipes;
        }

        @NonNull
        @Override
        public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ViewRecipeBinding binding = ViewRecipeBinding.inflate(getLayoutInflater(), parent, false);
            return new RecipeViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
            Recipe recipe = recipes.get(position);
            holder.bind(recipe);
        }

        @Override
        public int getItemCount() {
            return recipes.size();
        }

        class RecipeViewHolder extends RecyclerView.ViewHolder {
            ViewRecipeBinding binding;

            RecipeViewHolder(ViewRecipeBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            void bind(Recipe recipe) {
                binding.textView.setText(recipe.getTitle());
                binding.button3.setOnClickListener(view -> deleteRecipe(recipe, getAbsoluteAdapterPosition()));
            }

            private void deleteRecipe(Recipe recipe, int position) {
                RecipeDatabase.getDbInstance(FavoriteActivity.this).recipeDao().deleteRecipe(recipe);
                notifyItemRemoved(position);
                Snackbar.make(binding.getRoot(), getString(R.string.recipe_deleted, recipe.getTitle()), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.undo), view -> undoDelete(recipe, position))
                        .show();
            }

            private void undoDelete(Recipe recipe, int position) {
                RecipeDatabase.getDbInstance(FavoriteActivity.this).recipeDao().insertRecipe(recipe);
                notifyItemInserted(position);
            }
        }
    }
}
