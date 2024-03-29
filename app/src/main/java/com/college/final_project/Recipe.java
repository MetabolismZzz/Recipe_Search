package com.college.final_project;

/**
 * Represents a recipe with properties to hold necessary data like title, image URL, summary, and Spoonacular source URL.
 */
public class Recipe {
    private String title;
    private String imageUrl;
    private String summary;
    private String sourceUrl;

    /**
     * Constructs a new Recipe with default properties.
     */
    public Recipe() {
        // Default constructor
    }

    /**
     * Constructs a new Recipe with the specified title, image URL, summary, and source URL.
     *
     * @param title     The title of the recipe.
     * @param imageUrl  The URL of the recipe's image.
     * @param summary   A summary of the recipe.
     * @param sourceUrl The Spoonacular source URL of the recipe.
     */
    public Recipe(String title, String imageUrl, String summary, String sourceUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.summary = summary;
        this.sourceUrl = sourceUrl;
    }

    // Getter and setter methods for each property

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

    /**
     * Returns a string representation of the Recipe, which includes its title.
     *
     * @return A string representation of the Recipe.
     */
    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", summary='" + summary + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                '}';
    }
}