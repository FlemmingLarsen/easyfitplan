package dk.flemminglarsen.easyfitplan.Fragments.Weekdays;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dk.flemminglarsen.easyfitplan.Helperclasses.DatabaseHelper;
import dk.flemminglarsen.easyfitplan.R;


public class ExerciseListItemFragment extends Fragment {

    private static final String TAG = "EditDataActivity";

    private Button btnDelete;
    private TextView editable_item;
    DatabaseHelper mDatabaseHelper;
    private String selectedName;
    private Integer selectedID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_list_item, container, false);

        btnDelete = view.findViewById(R.id.btnDelete);
        mDatabaseHelper = new DatabaseHelper(getActivity());
        editable_item = view.findViewById(R.id.exerciseName);

        //If bundle is not empty, get data
        final Bundle bundle = getArguments();
        if (bundle != null) {
            selectedID = bundle.getInt("id", -1);
            selectedName = bundle.getString("name");
            editable_item.setText(selectedName);
        }

        //Delete exercise from database
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteName(selectedID, selectedName);
                editable_item.setText("");
                Toast.makeText(getActivity(), "Exercise deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
