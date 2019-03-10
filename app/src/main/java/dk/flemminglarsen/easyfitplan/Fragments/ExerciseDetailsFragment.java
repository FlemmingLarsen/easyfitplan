package dk.flemminglarsen.easyfitplan.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dk.flemminglarsen.easyfitplan.R;

public class ExerciseDetailsFragment extends Fragment {

    Toolbar mToolbar;
    ImageView mImageView;
    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_details, container, false);

        mToolbar = view.findViewById(R.id.toolbar2);
        mImageView = view.findViewById(R.id.imageView2);
        textView = view.findViewById(R.id.textView2);

        Bundle bundle = getArguments();
        if(bundle != null){
            mToolbar.setTitle(bundle.getString("chestExerciseNames"));
            mImageView.setImageResource(bundle.getInt("chestExerciseGifs"));
            textView.setText(bundle.getString("chestExerciseDescription"));
        }


        return view;
    }

}
