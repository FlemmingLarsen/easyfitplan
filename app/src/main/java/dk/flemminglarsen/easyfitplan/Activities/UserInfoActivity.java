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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dk.flemminglarsen.easyfitplan.R;

public class UserInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editName, editAge, editHeight, editWeight;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private Button profileUpdate;
    private FirebaseAuth firebaseAuth;
    /*private FirebaseDatabase firebaseDatabase;*/

    //Database
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public UserInfoActivity() {
    }

    /*DatabaseReference databaseUsers;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        radioGroup = findViewById(R.id.radioGrp);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.activityLevel, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        profileUpdate = findViewById(R.id.profileUpdate);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateDatabase();

                //Move this!!

            }
        });

        /*firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserActivity userProfile = dataSnapshot.getValue(UserActivity.class);
                editName.setText(UserActivity.getName());
                *//*editAge.setText(UserActivity.class.getAge());*//*

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserInfoActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });*/



    }

    //ToDo:  handle spinner and radiobutton Strings

    private void updateDatabase() {
        String name = editName.getText().toString().trim();
        String age = editAge.getText().toString().trim();
        String height = editHeight.getText().toString().trim();
        String weight = editWeight.getText().toString().trim();

        int selectedGender = radioGroup.getCheckedRadioButtonId();
        String gender = String.valueOf(selectedGender).trim();

        // Notice
        // Male String =   2131296423
        // Female String = 2131296421
        // To calculate BMR etc.



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

        //If conditions are met, update the database and go to profile
        else{
            Intent intent = new Intent(UserInfoActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }


        UserActivity userActivity = new UserActivity(name, age, gender, height, weight);
        databaseReference.push().setValue(userActivity);
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
