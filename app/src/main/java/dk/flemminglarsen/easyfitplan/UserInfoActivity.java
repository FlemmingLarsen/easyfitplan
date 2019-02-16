package dk.flemminglarsen.easyfitplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserInfoActivity extends AppCompatActivity {

    EditText editName, editAge, editGender, editHeight, editWeight;
    Button profileUpdate;

    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editGender = findViewById(R.id.editGender);
        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        profileUpdate = findViewById(R.id.profileUpdate);

        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

    }


    private void updateUser(){
        String name = editName.getText().toString().trim();
        String age = editAge.getText().toString().trim();
        String gender = editGender.getText().toString().trim();
        String height = editHeight.getText().toString().trim();
        String weight = editWeight.getText().toString().trim();

        //If the user enters information correctly, update Firebase
        //REMEMBER TO IMPLEMENT ERROR CEHCKING!!
        //Also - Stuffs need to be correct variables, not all Strings
        if(!TextUtils.isEmpty(name)) {
            String id = databaseUsers.push().getKey();
            UserActivity user = new UserActivity(id, name, age, gender, height, weight);
            databaseUsers.child(id).setValue(user);
            Toast.makeText(this, "User added to Firebase", Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(this, MenuActivity.class));

        }else{
            Toast.makeText(this, "Enter Name", Toast.LENGTH_LONG).show();
        }
    }

}
