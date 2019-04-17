package dk.flemminglarsen.easyfitplan.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dk.flemminglarsen.easyfitplan.Helperclasses.DatabaseHelper;
import dk.flemminglarsen.easyfitplan.R;


public class ExerciseListItemFragment extends Fragment {

    private static final String TAG = "EditDataActivity";
    DatabaseHelper mDatabaseHelper;
    private Button btnDelete;
    private TextView exercisename;
    private String selectedName;
    private Integer selectedID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_list_item, container, false);

        btnDelete = view.findViewById(R.id.btnDelete);
        mDatabaseHelper = new DatabaseHelper(getActivity());
        exercisename = view.findViewById(R.id.exerciseName);

        //If bundle is not empty, get data
        final Bundle bundle = getArguments();
        if (bundle != null) {
            selectedID = bundle.getInt("id", -1);
            selectedName = bundle.getString("name");
            exercisename.setText(selectedName);
        }

        //Delete exercise from database
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteName(selectedID, selectedName);
                exercisename.setText("");
                Toast.makeText(getActivity(),  selectedName + " deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
