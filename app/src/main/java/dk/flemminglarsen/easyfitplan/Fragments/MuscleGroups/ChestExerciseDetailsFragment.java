package dk.flemminglarsen.easyfitplan.Fragments.MuscleGroups;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dk.flemminglarsen.easyfitplan.Helperclasses.DatabaseHelper;
import dk.flemminglarsen.easyfitplan.R;

public class ChestExerciseDetailsFragment extends Fragment {

    Toolbar mToolbar;
    ImageView mImageView;
    TextView textView;
    Button btnAdd;
    String weekDays;
    String newEntry;

    DatabaseHelper mDatabaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_details, container, false);

        mToolbar = view.findViewById(R.id.toolbar2);
        mImageView = view.findViewById(R.id.imageView2);
        textView = view.findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        btnAdd = view.findViewById(R.id.btnAdd);

        //Get bundle and attach values to .xml
        final Bundle bundle = getArguments();
        if (bundle != null) {
            mToolbar.setTitle(bundle.getString("chestExerciseNames"));
            mImageView.setImageResource(bundle.getInt("chestExerciseGifs"));
            textView.setText(bundle.getString("chestExerciseDescription"));

            mDatabaseHelper = new DatabaseHelper(getActivity());

        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(getActivity(), btnAdd);
                popupMenu.getMenuInflater().inflate(R.menu.menu_week, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem weekDay) {

                        weekDays = weekDay.getTitle().toString();
                        newEntry = bundle.getString("chestExerciseNames");

                        //Check if that specific exercise is already on that specific day
                        if (newEntry != null) {
                            Boolean check = mDatabaseHelper.checkExists(newEntry, weekDays);
                            if (check == true) {
                                AddData(newEntry, weekDays);
                            } else {
                                Toast.makeText(getActivity(), newEntry + " is already on " + weekDays, Toast.LENGTH_SHORT).show();
                            }
                        }

                        return true;
                    }
                });

                popupMenu.show();
            }
        });

        return view;
    }

    //Add data to SQLite with name of exercise and day of the week if it's not present
    public void AddData(String newEntry, String weekDays) {
        boolean insertData = mDatabaseHelper.addData(newEntry, weekDays);

        if (insertData == true) {
            Toast.makeText(getActivity(), newEntry + " Added to " + weekDays, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}


