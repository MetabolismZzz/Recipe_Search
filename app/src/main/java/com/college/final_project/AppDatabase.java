package com.college.final_project;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Ensure entities and version are correctly set.
@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();

    // Use volatile to ensure atomic access to the variable
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {
        // Double-check locking pattern
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "recipes_database")
                            .fallbackToDestructiveMigration() // Handle migrations properly
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}