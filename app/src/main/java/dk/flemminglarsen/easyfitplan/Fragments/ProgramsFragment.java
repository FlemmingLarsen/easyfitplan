package dk.flemminglarsen.easyfitplan.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dk.flemminglarsen.easyfitplan.R;

public class ProgramsFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_programs, container, false);

        Button btnTraining = (Button)view.findViewById(R.id.buttonTraining);
        Button btnExercises = (Button)view.findViewById(R.id.buttonExercises);


        // Training and Excercise buttons to fragments
        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new TrainingFragment());
                fr.commit();
            }
        });

        btnExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ChooseGroupFragment());
                fr.commit();
            }
        });

        return view;
    }

}