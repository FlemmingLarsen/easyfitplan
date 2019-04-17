package dk.flemminglarsen.easyfitplan.Fragments.MuscleGroups;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import dk.flemminglarsen.easyfitplan.Helperclasses.ListviewAdapterExercises;
import dk.flemminglarsen.easyfitplan.R;


public class ChestExerciseListviewFragment extends Fragment {


    Toolbar mToolbar;
    ListView mListView;

    String[] chestExerciseNames = {"Barbell Press",
            "Dumbell flyers",
            "Pushups"};

    int[] chestExerciseGifs = {R.drawable.barbellbenchpress,
            R.drawable.dumbbellflyers,
            R.drawable.pushups};

    String[] chestExerciseDescription = {"Preparation\n" + "\n" + "Lie supine on bench. Dismount barbell from rack over upper chest using wide oblique overhand grip.\n" + "\n" + "Execution\n" + "\n" + "Lower weight to chest. Press bar upward until arms are extended. Repeat.\n" + "\n" +
            "Comments\n" + "Range of motion will be compromised if grip is too wide.",

            "Preparation\n" + "\n" + "Grasp two dumbbells. Lie supine on bench. Support dumbbells above chest with arms fixed in slightly bent position. Internally rotate shoulders so elbows point out to sides.\n" + "\n" + "Execution\n" + "\n" +
                    "Lower dumbbells to sides until chest muscles are stretched with elbows fixed in slightly bent position. Bring dumbbells together in wide hugging motion until dumbbells are nearly together. Repeat.\n" + "\n" + "Comments\n" +
                    "Keep shoulders internally rotated so elbows point downward at bottom position and outward at top position. Keep elbows at fixed angle, only slightly bent.",

            "Preparation\n" + "\n" + "Lie prone on floor with hands slightly wider than shoulder width. Raise body up off floor by extending arms with body straight.\n" + "\n" + "Execution\n" + "\n" +
                    "Keeping body straight, lower body to floor by bending arms. Push body up until arms are extended. Repeat.\n" + "\n" + "Comments\n" + "Both upper and lower body must be kept straight throughout movement."};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_listview_chest, container, false);

        mToolbar = view.findViewById(R.id.toolbar);
        mListView = view.findViewById(R.id.listview);
        mToolbar.setTitle(getResources().getString(R.string.chestExercises));

        ListviewAdapterExercises mAdapter = new ListviewAdapterExercises(getActivity(), chestExerciseNames, chestExerciseGifs, chestExerciseDescription);
        mListView.setAdapter(mAdapter);

        //OnItemClick for the listview items
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                ChestExerciseDetailsFragment frt = new ChestExerciseDetailsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                //Send the relevant data to the ChestExerciseDetailsFragment
                Bundle bundle = new Bundle();
                bundle.putString("chestExerciseNames", chestExerciseNames[i]);
                bundle.putInt("chestExerciseGifs", chestExerciseGifs[i]);
                bundle.putString("chestExerciseDescription", chestExerciseDescription[i]);

                frt.setArguments(bundle);
                transaction.replace(R.id.fragment_container, frt);
                transaction.addToBackStack(null).commit();

            }
        });

        return view;
    }
}
