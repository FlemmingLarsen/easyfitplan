package dk.flemminglarsen.easyfitplan.Fragments;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dk.flemminglarsen.easyfitplan.Helperclasses.listviewAdapterExercises;
import dk.flemminglarsen.easyfitplan.Helperclasses.listviewAdapterWeek;
import dk.flemminglarsen.easyfitplan.R;

public class TrainingFragment extends Fragment {

    private ExpandableListView listView;
    private listviewAdapterWeek listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        Button btnExercises = view.findViewById(R.id.buttonExercises);
        Button btnPrograms = view.findViewById(R.id.buttonPrograms);
        Bundle bundle = getArguments();
        TextView textView = view.findViewById(R.id.test);

        /*String value = this.getArguments().getString("key");//get your parameters
        Toast.makeText(getActivity(), value+" ", Toast.LENGTH_LONG).show();//show data in tost*/

        // Exercise and Programs buttons to fragments
        btnExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ChooseGroupFragment());
                fr.addToBackStack(null).commit();
            }
        });

        btnPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction frt = getFragmentManager().beginTransaction();
                frt.replace(R.id.fragment_container, new ProgramsFragment());
                frt.addToBackStack(null).commit();
            }
        });

        listView = (ExpandableListView)view.findViewById(R.id.weekDays);
        initData();
        listAdapter = new listviewAdapterWeek(getActivity(), listDataHeader, listHash);
        listView.setAdapter(listAdapter);


        return view;
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        //Headers. Days.
        listDataHeader.add("Monday");
        listDataHeader.add("Tuesday");
        listDataHeader.add("Wednesday");
        listDataHeader.add("Thursday");
        listDataHeader.add("Friday");
        listDataHeader.add("Saturday");
        listDataHeader.add("Sunday");

        //Add items to the week days
        List<String> Monday = new ArrayList<>();
        Monday.add("Working as intended");

        List<String> Tuesday = new ArrayList<>();
        Tuesday.add("This is where they go!!");

        List<String> Wednesday = new ArrayList<>();
        Wednesday.add("This is where they go!!");

        List<String> Thursday = new ArrayList<>();
        Thursday.add("This is where they go!!");

        List<String> Friday = new ArrayList<>();
        Friday.add("This is where they go!!");

        List<String> Saturday = new ArrayList<>();
        Saturday.add("This is where they go!!");

        List<String> Sunday = new ArrayList<>();
        Sunday.add("This is where they go!!");

        listHash.put(listDataHeader.get(0), Monday);
        listHash.put(listDataHeader.get(1), Tuesday);
        listHash.put(listDataHeader.get(2), Wednesday);
        listHash.put(listDataHeader.get(3), Thursday);
        listHash.put(listDataHeader.get(4), Friday);
        listHash.put(listDataHeader.get(5), Saturday);
        listHash.put(listDataHeader.get(6), Sunday);
    }

}