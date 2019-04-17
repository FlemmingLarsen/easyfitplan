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


public class ShoulderExerciseListviewFragment extends Fragment {

    Toolbar mToolbar;
    ListView mListView;

    String[] shoulderExerciseNames = {"Barbell Military Press",
            "Dumbell Lateral Raise",
            "Dumbell Shoulder Press"};

    int[] shoulderExerciseGifs = {R.drawable.barbellmilitarypress,
            R.drawable.dumbbelllateralraise,
            R.drawable.dumbellshoulderpress};

    String[] shoulderExerciseDescription = {"Preparation\n" + "\n" + "Grasp dumbbells in front of thighs with elbows slightly bent. Bend over slightly with hips and knees bent slightly.\n" + "\n" + "Execution\n" + "\n" + "Raise upper arms to sides until elbows are shoulder height. " +
            "Maintain elbows' height above or equal to wrists. Lower and repeat.\n" + "\n" + "Comments\n" + "\n" + "Keep elbows pointed high while maintaining slight bend through elbows (10° to 30° angle) throughout movement. " +
            "At top of movement, elbows (not necessarily dumbbells) should be directly lateral to shoulders since elbows are slightly bent forward. Dumbbells are raised by shoulder abduction, not external rotation. " +
            "If elbows drop lower than wrists, front deltoids become primary mover instead of lateral deltoids. To keep resistance targeted to side delt, torso is bent over slightly.",

            "Preparation\n" + "\n" + "Grasp barbell from rack or clean barbell from floor with overhand grip, slightly wider than shoulder width. Position bar in front of neck.\n" + "\n" + "Execution\n" + "\n" + "Press bar upward until arms are extended overhead. " +
                    "Lower to front of neck and repeat.\n" + "\n" + "Comments\n" + "\n" + "See unrack and rack technique. Feet may be positioned shoulder width apart or one foot in front of other with forward leg slightly bent (as shown). " +
                    "Upper chest assists (instead of side delts) since grip is slightly narrower and chest is high with low back arched back slightly.",

            "Preparation\n" + "\n" + "Position dumbbells to each side of shoulders with elbows below wrists.\n" + "\n" + "Execution\n" + "\n" + "Press dumbbells upward until arms are extended overhead. Lower to sides of shoulders and repeat."};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_listview_shoulder, container, false);

        mToolbar = view.findViewById(R.id.toolbar);
        mListView = view.findViewById(R.id.listview);
        mToolbar.setTitle(getResources().getString(R.string.shoulderExercises));

        ListviewAdapterExercises mAdapter = new ListviewAdapterExercises(getActivity(), shoulderExerciseNames, shoulderExerciseGifs, shoulderExerciseDescription);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                ShoulderExerciseDetailsFragment frt = new ShoulderExerciseDetailsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("shoulderExerciseNames", shoulderExerciseNames[i]);
                bundle.putInt("shoulderExerciseGifs", shoulderExerciseGifs[i]);
                bundle.putString("shoulderExerciseDescription", shoulderExerciseDescription[i]);

                frt.setArguments(bundle);
                transaction.replace(R.id.fragment_container, frt);
                transaction.addToBackStack(null).commit();
            }
        });

        return view;
    }
}
