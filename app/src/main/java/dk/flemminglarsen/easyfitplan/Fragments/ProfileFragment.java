package dk.flemminglarsen.easyfitplan.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.UUID;

import dk.flemminglarsen.easyfitplan.Activities.UserActivity;
import dk.flemminglarsen.easyfitplan.R;



public class ProfileFragment extends Fragment {

    public TextView profileName, profileBMR, profileTDEE;
    Double BMR, multiplyer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileName = view.findViewById(R.id.txtName);
        profileBMR = view.findViewById(R.id.txtBMR);
        profileTDEE = view.findViewById(R.id.txtActivity);


    //Get user from Firebase
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = rootRef.child("users").child(uid);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                profileName.setText(name);
                String age = dataSnapshot.child("age").getValue(String.class);
                String gender = dataSnapshot.child("gender").getValue(String.class);
                String height = dataSnapshot.child("height").getValue(String.class);
                String weight = dataSnapshot.child("weight").getValue(String.class);


     //Parse Strings to Integers so BMR and TDEE can be calculated
    int ageInt = Integer.parseInt(age);
    int heightInt = Integer.parseInt(height);
    int weightInt = Integer.parseInt(weight);

    //Calculate BMR for either Male or Female
    if(gender.equals("Male")){
        BMR = (10 * weightInt) + (6.25 * heightInt) - (5 * ageInt) +5;
        String BRMresult = Double.toString(BMR);
        profileBMR.setText(BRMresult);
    }if(gender.equals("Female")){
        BMR = (10 * weightInt) + (6.25 * heightInt) - (5 * ageInt) -161;
        String BRMresult = Double.toString(BMR);
        profileBMR.setText(BRMresult);
    }

    //Get data from spinner and calculate TDEE
    String activity = dataSnapshot.child("activity").getValue(String.class);
    if(activity.equals("Sedentary (No Exercise)")){
        multiplyer = BMR * 1.2;
        String result = Double.toString(multiplyer);
        profileTDEE.setText(result);
    }
    if(activity.equals("Light Exercise (1–2 days/week)")){
        multiplyer = BMR * 1.375;
        String result = Double.toString(multiplyer);
        profileTDEE.setText(result);
    }
    if(activity.equals("Moderate Exercise (3–5 days/week)")){
        multiplyer = BMR * 1.55;
        String result = Double.toString(multiplyer);
        profileTDEE.setText(result);
    }
    if(activity.equals("Heavy Exercise (6–7 days/week)")){
        multiplyer = BMR * 1.725;
        String result = Double.toString(multiplyer);
        profileTDEE.setText(result);
    }
    if(activity.equals("Athlete 2 times per day")){
        multiplyer = BMR * 1.9;
        String result = Double.toString(multiplyer);
        profileTDEE.setText(result);
    }





}

        @Override
        public void onCancelled(DatabaseError databaseError) {}
    };
    usersRef.addListenerForSingleValueEvent(valueEventListener);


        //ToDo: Get, calculate and show info from Firebase


        //ToDo: Calculate TDEE, BRM etc. on profile fragment


        //ToDo: Implement users ability to edit database information

        return view;

        }


    }


