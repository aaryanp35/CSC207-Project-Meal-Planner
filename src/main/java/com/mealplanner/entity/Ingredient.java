package com.mealplanner.entity;

// Core entity representing a single ingredient with nutritional information. SHELL CLASS ONLY - INGRIENTS IMPLEMENTED IN RECIPIE ENTITY
// Responsible: Aaryan
public class Ingredient {
    private final String name;
    private final double quantity;
    private final Unit unit;
    private final NutritionInfo nutritionInfo;

   
    public Ingredient(String name, double quantity, Unit unit, NutritionInfo nutritionInfo) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (nutritionInfo == null) {
            throw new IllegalArgumentException("NutritionInfo cannot be null");
        }

        this.name = name.trim();
        this.quantity = quantity;
        this.unit = unit;
        this.nutritionInfo = nutritionInfo;
    }

    //The name of the ingredient
    public String getName() {
        return name;
    }

    //The quantity of the ingredient
    public double getQuantity() {
        return quantity;
    }

    //The unit of measurement
    public Unit getUnit() {
        return unit;
    }

    //returns the nutritional information
    public NutritionInfo getNutritionInfo() {
        return nutritionInfo;
    }

    
      //Creates a new Ingredient with an updated quantity while preserving all other properties.
    
    public Ingredient withQuantity(double newQuantity) {
        return new Ingredient(this.name, newQuantity, this.unit, this.nutritionInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;
        return Double.compare(that.quantity, quantity) == 0 &&
                name.equals(that.name) &&
                unit == that.unit &&
                nutritionInfo.equals(that.nutritionInfo);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        long temp = Double.doubleToLongBits(quantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + unit.hashCode();
        result = 31 * result + nutritionInfo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f %s)", name, quantity, unit);
    }
}
