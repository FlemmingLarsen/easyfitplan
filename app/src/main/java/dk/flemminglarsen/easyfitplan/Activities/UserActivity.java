package dk.flemminglarsen.easyfitplan.Activities;

public class UserActivity {
    public static String name;
    public static String age;
    public static String gender;
    public static String height;
    public static String weight;

    public UserActivity(){

    }


    public UserActivity(String name, String age, String gender, String height, String weight) {
        UserActivity.name = name;
        UserActivity.age = age;
        UserActivity.gender = gender;
        UserActivity.height = height;
        UserActivity.weight = weight;
    }



    //Getters
    public static String getName() {
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
        UserActivity.name = name;
    }

    public void setAge(String age) {
        UserActivity.age = age;
    }

    public void setGender(String gender) {
        UserActivity.gender = gender;
    }

    public void setHeight(String height) {
        UserActivity.height = height;
    }

    public void setWeight(String weight) {
        UserActivity.weight = weight;
    }
}
