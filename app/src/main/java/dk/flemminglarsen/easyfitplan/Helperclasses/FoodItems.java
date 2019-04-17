package dk.flemminglarsen.easyfitplan.Helperclasses;

public class FoodItems {
    private String food;
    private String proteins;
    private String fats;
    private String carbs;

    public FoodItems(String food, String proteins, String fats, String carbs) {
        this.food = food;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }

    public FoodItems() {

    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getProteins() {
        return proteins;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }
}
