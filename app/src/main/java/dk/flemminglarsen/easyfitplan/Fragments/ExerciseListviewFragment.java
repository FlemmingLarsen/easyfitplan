package dk.flemminglarsen.easyfitplan.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;

import dk.flemminglarsen.easyfitplan.Activities.MainActivity;
import dk.flemminglarsen.easyfitplan.Helperclasses.listviewAdapter;
import dk.flemminglarsen.easyfitplan.R;


public class ExerciseListviewFragment extends Fragment {


    Toolbar mToolbar;
    ListView mListView;

    String[] chestExerciseNames = {"Barbell Press",
                                    "Dumbell flyers",
                                    "Pushups"};

    int[] chestExerciseGifs = {R.drawable.barbellbenchpress,
                                R.drawable.dumbbellflyers,
                                R.drawable.pushups};

    String[] chestExerciseDescription = {"bench_press",
                                        "Two",
                                        "Three"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_exercise_listview, container, false);

          mToolbar = view.findViewById(R.id.toolbar);
          mListView = view.findViewById(R.id.listview);
          mToolbar.setTitle(getResources().getString(R.string.chestExercises));

          listviewAdapter mAdapter = new listviewAdapter(getActivity(), chestExerciseNames, chestExerciseGifs, chestExerciseDescription);
          mListView.setAdapter(mAdapter);

          mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                  ExerciseDetailsFragment frt = new ExerciseDetailsFragment();
                  FragmentTransaction transaction = getFragmentManager().beginTransaction();

                  Bundle bundle = new Bundle();
                  bundle.putString("chestExerciseNames", chestExerciseNames[i]);
                  bundle.putInt("chestExerciseGifs", chestExerciseGifs[i]);
                  bundle.putString("chestExerciseDescription", chestExerciseDescription[i]);

                  frt.setArguments(bundle);
                  transaction.replace(R.id.fragment_container, frt);
                  transaction.commit();

              }
          });



       return view;
    }


}
