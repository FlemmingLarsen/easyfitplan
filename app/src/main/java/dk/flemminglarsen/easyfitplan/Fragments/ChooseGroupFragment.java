package dk.flemminglarsen.easyfitplan.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dk.flemminglarsen.easyfitplan.Fragments.MuscleGroups.ChestExerciseListviewFragment;
import dk.flemminglarsen.easyfitplan.R;

public class ChooseGroupFragment extends Fragment {


    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_group, container, false);


        //ToDo: At least two more muscle groups
        //Top navigation buttons
        Button btnTraining = view.findViewById(R.id.buttonTraining);
        Button btnPrograms = view.findViewById(R.id.buttonPrograms);

        //Muscle buttons
        Button btnHead = view.findViewById(R.id.headButton);
        Button btnChest = view.findViewById(R.id.chestButton);

       /* final int[] x = new int[1];
        final int[] y = new int[1];

        RelativeLayout exerciseFragment = null;
        exerciseFragment = (RelativeLayout)view.findViewById(R.id.exerciseScreen);

        //OnTouch for bodyimage
        exerciseFragment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x[0] = (int) event.getX();
                y[0] = (int) event.getY();

                Toast.makeText(getContext(), "Testing", Toast.LENGTH_SHORT).show();

                // x1            y1            x2            y2             ????????????????
                if(x[0] > 250 && y[0] > 100 && x[0] < 375 && y[0] < 300){
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new EducationFragment());
                    fr.commit();
                }
                return true;
            }
        });
*/

        //Exercise and Programs buttons to fragments
        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new TrainingFragment());
                fr.commit();
            }
        });

        btnPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ProgramsFragment());
                fr.commit();
            }
        });

        //Muscle buttons
        btnHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new EducationFragment());
                fr.commit();
            }
        });

        btnChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ChestExerciseListviewFragment());
                fr.commit();
            }
        });

        return view;
    }


}