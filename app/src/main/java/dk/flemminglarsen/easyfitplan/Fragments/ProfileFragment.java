package dk.flemminglarsen.easyfitplan.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dk.flemminglarsen.easyfitplan.R;


public class ProfileFragment extends Fragment {

    public TextView profileName, profileBMR, profileTDEE, weightLoss, weightStay, weightGain;
    Double BMR, multiplyer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileName = view.findViewById(R.id.txtName);
        profileBMR = view.findViewById(R.id.txtBMRInfo);
        profileTDEE = view.findViewById(R.id.txtTDEEInfo);
        weightLoss = view.findViewById(R.id.weightLoss);
        weightStay = view.findViewById(R.id.weightStay);
        weightGain = view.findViewById(R.id.weightGain);

        //Get user from Firebase
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = rootRef.child("users").child(uid);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String placeholder = getResources().getString(R.string.hello_user);
                String welcome = String.format(placeholder, name);
                profileName.setText(welcome);

                String age = dataSnapshot.child("age").getValue(String.class);
                String gender = dataSnapshot.child("gender").getValue(String.class);
                String height = dataSnapshot.child("height").getValue(String.class);
                String weight = dataSnapshot.child("weight").getValue(String.class);


                //Parse Strings to Integers so BMR and TDEE can be calculated
                int ageInt = Integer.parseInt(age);
                int heightInt = Integer.parseInt(height);
                int weightInt = Integer.parseInt(weight);

                //Calculate BMR for either Male or Female
                if (gender.equals("Male")) {
                    BMR = (10 * weightInt) + (6.25 * heightInt) - (5 * ageInt) + 5;
                    String BRMresult = String.format("%.0f", BMR);
                    String BMRplaceholder = getResources().getString(R.string.metabolic_rate);
                    String BMRShow = String.format(BMRplaceholder, BRMresult);
                    profileBMR.setText(BMRShow);

                }
                if (gender.equals("Female")) {
                    BMR = (10 * weightInt) + (6.25 * heightInt) - (5 * ageInt) - 161;
                    String BRMresult = String.format("%.0f", BMR);
                    String BMRplaceholder = getResources().getString(R.string.metabolic_rate);
                    String BMRShow = String.format(BMRplaceholder, BRMresult);
                    profileBMR.setText(BMRShow);
                }

                //Get data from spinner and calculate TDEE
                String activity = dataSnapshot.child("activity").getValue(String.class);
                if (activity.equals("Sedentary (No Exercise)")) {
                    multiplyer = BMR * 1.2;
                    String result = String.format("%.0f", multiplyer);
                    String TDEEplaceholder = getResources().getString(R.string.energy_expenditure);
                    String TDEEShow = String.format(TDEEplaceholder, result);
                    profileTDEE.setText(TDEEShow);
                }
                if (activity.equals("Light Exercise (1–2 days/week)")) {
                    multiplyer = BMR * 1.375;
                    String result = String.format("%.0f", multiplyer);
                    String TDEEplaceholder = getResources().getString(R.string.energy_expenditure);
                    String TDEEShow = String.format(TDEEplaceholder, result);
                    profileTDEE.setText(TDEEShow);
                }
                if (activity.equals("Moderate Exercise (3–5 days/week)")) {
                    multiplyer = BMR * 1.55;
                    String result = String.format("%.0f", multiplyer);
                    String TDEEplaceholder = getResources().getString(R.string.energy_expenditure);
                    String TDEEShow = String.format(TDEEplaceholder, result);
                    profileTDEE.setText(TDEEShow);
                }
                if (activity.equals("Heavy Exercise (6–7 days/week)")) {
                    multiplyer = BMR * 1.725;
                    String result = String.format("%.0f", multiplyer);
                    String TDEEplaceholder = getResources().getString(R.string.energy_expenditure);
                    String TDEEShow = String.format(TDEEplaceholder, result);
                    profileTDEE.setText(TDEEShow);
                }
                if (activity.equals("Athlete 2 times per day")) {
                    multiplyer = BMR * 1.9;
                    String result = String.format("%.0f", multiplyer);
                    String TDEEplaceholder = getResources().getString(R.string.energy_expenditure);
                    String TDEEShow = String.format(TDEEplaceholder, result);
                    profileTDEE.setText(TDEEShow);
                }

                //Multiplyers for loss, stay or gain weight
                Double loss = multiplyer - 500;
                String calLos = String.format("%.0f", loss);
                String lossPlaceholder = getResources().getString(R.string.weight_loss);
                String lossShow = String.format(lossPlaceholder, calLos);
                weightLoss.setText(lossShow);

                Double maintain = multiplyer;
                String calMain = String.format("%.0f", maintain);
                String maintainPlaceholder = getResources().getString(R.string.weight_stay);
                String maintainShow = String.format(maintainPlaceholder, calMain);
                weightStay.setText(maintainShow);
                saveCalories(calMain);

                Double gain = multiplyer + 500;
                String calGain = String.format("%.0f", gain);
                String gainPlaceholder = getResources().getString(R.string.weight_gain);
                String gainShow = String.format(gainPlaceholder, calGain);
                weightGain.setText(gainShow);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        usersRef.addListenerForSingleValueEvent(valueEventListener);

        return view;
    }

    //Save calories to shared preferences
    public void saveCalories(String calMain) {
        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.putString("calories", calMain);
        edt.apply();
    }
}


