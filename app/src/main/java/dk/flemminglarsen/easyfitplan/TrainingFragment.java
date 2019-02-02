package dk.flemminglarsen.easyfitplan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TrainingFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        Button btnExercises = view.findViewById(R.id.buttonExercises);
        Button btnPrograms = view.findViewById(R.id.buttonPrograms);


        // Exercise and Programs buttons to fragments
        btnExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ExerciseFragment());
                fr.commit();
            }
        });

        btnPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction frt = getFragmentManager().beginTransaction();
                frt.replace(R.id.fragment_container, new ProgramsFragment());
                frt.commit();
            }
        });
        return view;
    }

}