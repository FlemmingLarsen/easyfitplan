package dk.flemminglarsen.easyfitplan;

import android.support.v7.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {
    public String name, email, age, sex, height, weight;

    public UserActivity(String name, String email){

    }

    public UserActivity(String name, String email, String age, String sex, String height, String weight) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
    }
}
