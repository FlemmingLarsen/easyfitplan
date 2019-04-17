package dk.flemminglarsen.easyfitplan.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dk.flemminglarsen.easyfitplan.R;

public class TrainingFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        Button btnExercises = view.findViewById(R.id.buttonExercises);
        Button btnPrograms = view.findViewById(R.id.buttonPrograms);
        Button btnMonday = view.findViewById(R.id.btnMonday);
        Button btnTuesday = view.findViewById(R.id.btnTuesday);
        Button btnWednesday = view.findViewById(R.id.btnWednesday);
        Button btnThursday = view.findViewById(R.id.btnThursday);
        Button btnFriday = view.findViewById(R.id.btnFriday);
        Button btnSaturday = view.findViewById(R.id.btnSaturday);
        Button btnSunday = view.findViewById(R.id.btnSunday);

        // Exercise and Programs buttons to fragments
        btnExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ChooseGroupFragment());
                fr.addToBackStack(null).commit();
            }
        });

        btnPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction frt = getFragmentManager().beginTransaction();
                frt.replace(R.id.fragment_container, new ProgramsFragment());
                frt.addToBackStack(null).commit();
            }
        });

        //Weekday buttons
        btnMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new WeekFragment();
                Bundle bundle = new Bundle();
                String monday = "monday";
                bundle.putString("day", monday);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });
        btnTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new WeekFragment();
                Bundle bundle = new Bundle();
                String tuesday = "tuesday";
                bundle.putString("day", tuesday);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });
        btnWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new WeekFragment();
                Bundle bundle = new Bundle();
                String wednesday = "wednesday";
                bundle.putString("day", wednesday);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });
        btnThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new WeekFragment();
                Bundle bundle = new Bundle();
                String thursday = "thursday";
                bundle.putString("day", thursday);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });
        btnFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new WeekFragment();
                Bundle bundle = new Bundle();
                String friday = "friday";
                bundle.putString("day", friday);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });
        btnSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new WeekFragment();
                Bundle bundle = new Bundle();
                String saturday = "saturday";
                bundle.putString("day", saturday);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });
        btnSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new WeekFragment();
                Bundle bundle = new Bundle();
                String sunday = "sunday";
                bundle.putString("day", sunday);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
