package com.college.final_project;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Represents a recipe with properties to hold necessary data like title, image URL, summary, and Spoonacular source URL.
 */
@Entity(tableName = "recipes") // Defines the table name
public class Recipe {
    @PrimaryKey(autoGenerate = true) // Auto-generate ID for each entry
    private int id; // Unique ID for the Recipe

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "summary")
    private String summary;

    @ColumnInfo(name = "source_url")
    private String sourceUrl;

    // Constructor, getters, and setters

    public Recipe() {
        // Default constructor
    }

    public Recipe(String title, String imageUrl, String summary, String sourceUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.summary = summary;
        this.sourceUrl = sourceUrl;
    }

    // ID getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", summary='" + summary + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                '}';
    }
}