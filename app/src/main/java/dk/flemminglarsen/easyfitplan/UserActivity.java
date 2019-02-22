package dk.flemminglarsen.easyfitplan;

public class UserActivity {
    public String name;
    public String age;
    public String gender;
    public String height;
    public String weight;

    public UserActivity(){

    }


    public UserActivity(String name, String age, String gender, String height, String weight) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }



    //Getters
    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return gender;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }


    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
