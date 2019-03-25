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

import dk.flemminglarsen.easyfitplan.Fragments.Weekdays.MondayFragment;
import dk.flemminglarsen.easyfitplan.R;

public class TrainingFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        Button btnExercises = view.findViewById(R.id.buttonExercises);
        Button btnPrograms = view.findViewById(R.id.buttonPrograms);
        Button btnMonday = view.findViewById(R.id.btnMonday);
        TextView textView = view.findViewById(R.id.test);

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

        btnMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction frt = getFragmentManager().beginTransaction();
                frt.replace(R.id.fragment_container, new MondayFragment());
                frt.addToBackStack(null).commit();
            }
        });

        return view;
    }

}
