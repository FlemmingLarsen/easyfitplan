package dk.flemminglarsen.easyfitplan.Helperclasses;

public class UserActivity {
    public String name, age, gender, height, weight, activity;


    public UserActivity() {

    }

    public UserActivity(String name, String age, String gender, String height, String weight, String activity) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.name = activity;
    }


}

