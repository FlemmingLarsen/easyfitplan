package dk.flemminglarsen.easyfitplan.Fragments.MuscleGroups;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import dk.flemminglarsen.easyfitplan.R;

public class ChestExerciseDetailsFragment extends Fragment {

    Toolbar mToolbar;
    ImageView mImageView;
    TextView textView;
    Button btnAdd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_details, container, false);

        mToolbar = view.findViewById(R.id.toolbar2);
        mImageView = view.findViewById(R.id.imageView2);
        textView = view.findViewById(R.id.textView2);
        btnAdd = view.findViewById(R.id.btnAdd);

        Bundle bundle = getArguments();
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
                        Toast.makeText(getActivity(), "Added to "+ item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;

                    }
                });

                popupMenu.show();
            }
        });

        return view;
    }


}