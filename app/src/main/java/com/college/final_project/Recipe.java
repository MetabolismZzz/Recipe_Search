package com.college.final_project;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    public int id;

    @ColumnInfo(name="title")
    public String title;

    @ColumnInfo(name="image_url")
    public String imageUrl;

    @ColumnInfo(name="summary")
    public String summary;

    @ColumnInfo(name="source_url")
    public String sourceUrl;

    // Default constructor
    public Recipe() {}

    // Parameterized constructor
    public Recipe(String title, String imageUrl, String summary, String sourceUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.summary = summary;
        this.sourceUrl = sourceUrl;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setImageUrl(String image) {
    }

    public void setTitle(String title) {
    }
}
