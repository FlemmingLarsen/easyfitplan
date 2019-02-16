package dk.flemminglarsen.easyfitplan;

public class UserActivity {

    private String id;
    private String name;
    private String age;
    private String gender;
    private String height;
    private String weight;


    UserActivity(){

    }

    public UserActivity(String id, String name, String age, String gender, String height, String weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

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
}
