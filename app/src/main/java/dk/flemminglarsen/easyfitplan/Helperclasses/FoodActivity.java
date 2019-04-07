package dk.flemminglarsen.easyfitplan.Helperclasses;

public class FoodActivity extends FoodItems {

     String name;
     String carbohydrates;
     String fats;
     String protein;

    public FoodActivity() {
    }

    public FoodActivity(String name, String carbohydrates, String fats, String protein) {
        this.name = name;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.protein = protein;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }
}
