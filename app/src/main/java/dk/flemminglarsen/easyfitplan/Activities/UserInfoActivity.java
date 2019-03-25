package dk.flemminglarsen.easyfitplan.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import dk.flemminglarsen.easyfitplan.Helperclasses.UserActivity;
import dk.flemminglarsen.easyfitplan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editName, editAge, editHeight, editWeight;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    Spinner spinner;
    Button profileUpdate;
    int selectValueId;
    String gender, activity;

    //Database
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public UserInfoActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        radioGroup = findViewById(R.id.radioGrp);
        radioButton = findViewById(R.id.radioM);
        spinner = findViewById(R.id.spinner);
        profileUpdate = findViewById(R.id.profileUpdate);

        //Reference to Firebase "users"
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        //Select Activity level spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.activityLevel, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        //When button is pressed retrive gender and activity variables and update profile
        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Radiobuttons genders
                selectValueId = radioGroup.getCheckedRadioButtonId();
                if(selectValueId == radioButton.getId()){
                    gender = "Male";
                }else{
                    gender = "Female";
                }

                //Spinner activity level
                activity = spinner.getSelectedItem().toString();


                //Call
                updateDatabase();

            }
        });


    }

    //Update user info i realtime database
    private void updateDatabase() {

        String name = editName.getText().toString().trim();
        String age = editAge.getText().toString().trim();
        String height = editHeight.getText().toString().trim();
        String weight = editWeight.getText().toString().trim();

        //Conditions for user information
        if (name.isEmpty()) {
            editName.setError("Name is required");
            editName.requestFocus();
            return;
        }

        if (age.isEmpty()) {
            editAge.setError("Age is required");
            editAge.requestFocus();
            return;
        }

        if (height.isEmpty()) {
            editHeight.setError("Height is required");
            editHeight.requestFocus();
            return;
        }

        if (weight.isEmpty()) {
            editWeight.setError("Weight is required");
            editWeight.requestFocus();
            return;
        }

        //If conditions are met create user in Firebase and go to users profile
        else{
            String UID = FirebaseAuth.getInstance().getUid();
            DatabaseReference current_user = databaseReference.child(UID);
            current_user.child("name").setValue(name);
            current_user.child("age").setValue(age);
            current_user.child("gender").setValue(gender);
            current_user.child("height").setValue(height);
            current_user.child("weight").setValue(weight);
            current_user.child("activity").setValue(activity);

            Intent intent = new Intent(UserInfoActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String activityLevel = parent.getItemAtPosition(position).toString().trim();
        Toast.makeText(parent.getContext(), activityLevel, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

 }

