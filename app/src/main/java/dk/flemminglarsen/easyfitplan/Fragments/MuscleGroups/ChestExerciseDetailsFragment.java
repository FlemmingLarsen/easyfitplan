package dk.flemminglarsen.easyfitplan.Fragments.MuscleGroups;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dk.flemminglarsen.easyfitplan.Fragments.TrainingFragment;
import dk.flemminglarsen.easyfitplan.R;

public class ChestExerciseDetailsFragment extends Fragment {

    Toolbar mToolbar;
    ImageView mImageView;
    TextView textView;
    Button btnAdd;
    String chestExerciseNames, chestExerciseGifs, chestExerciseDescription;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_details, container, false);

        mToolbar = view.findViewById(R.id.toolbar2);
        mImageView = view.findViewById(R.id.imageView2);
        textView = view.findViewById(R.id.textView2);
        btnAdd = view.findViewById(R.id.btnAdd);

        final Bundle bundle = getArguments();
        if(bundle != null){
            mToolbar.setTitle(bundle.getString("chestExerciseNames"));
            mImageView.setImageResource(bundle.getInt("chestExerciseGifs"));
            textView.setText(bundle.getString("chestExerciseDescription"));


        }

        //ToDo: Add Exercises to week days
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(getActivity(), btnAdd);
                popupMenu.getMenuInflater().inflate(R.menu.menu_week, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {


                        Bundle bundle = new Bundle();
                        bundle.putString("key","Jhon Doe"); // set your parameteres

                        TrainingFragment nextFragment = new TrainingFragment();
                        nextFragment.setArguments(bundle);

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.test, nextFragment).commit();


                        return true;



                    }
                });

                popupMenu.show();
            }
        });

        return view;
    }


}
