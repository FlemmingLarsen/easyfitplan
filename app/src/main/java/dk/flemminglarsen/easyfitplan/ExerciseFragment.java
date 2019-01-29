package dk.flemminglarsen.easyfitplan;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

public class ExerciseFragment extends Fragment {


    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_excercise, container, false);

        //Top navigation buttons
        Button btnTraining = (Button)view.findViewById(R.id.buttonTraining);
        Button btnPrograms = (Button)view.findViewById(R.id.buttonPrograms);

        //Muscle buttons
        Button btnHead = (Button)view.findViewById(R.id.headButton);

        final int[] x = new int[1];
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

        btnHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ProgramsFragment());
                fr.commit();
            }
        });

        return view;
    }


}