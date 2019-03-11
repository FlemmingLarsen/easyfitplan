package dk.flemminglarsen.easyfitplan.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dk.flemminglarsen.easyfitplan.R;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView txtAge = view.findViewById(R.id.txtAge);
        TextView txtName = view.findViewById(R.id.txtName);
        Button updateProfile = view.findViewById(R.id.btnUpdate);

        //ToDo: Get, calculate and show info from Firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String email = user.getEmail();
            txtAge.setText(email);

            String name = user.getUid();
            txtName.setText(name);

        //ToDo: Calculate TDEE, BRM etc. on profile fragment


        //ToDo: Implement users ability to edit database information
        }

        return view;
    }

}
