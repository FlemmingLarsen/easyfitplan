package dk.flemminglarsen.easyfitplan.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import dk.flemminglarsen.easyfitplan.Helperclasses.DatabaseHelper;
import dk.flemminglarsen.easyfitplan.R;


public class WeekFragment extends Fragment {

    private static final String TAG = "ListDataActivity";
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;
    String day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training_day, container, false);

        mListView = view.findViewById(R.id.listview);
        mDatabaseHelper = new DatabaseHelper(getActivity());

        populateListView();

        return view;
    }

    //Get data from column 2 and add it to the listview
    private void populateListView() {

        //If bundle is not empty, get "day" from Trainingfragment
        final Bundle bundle = getArguments();
        if (bundle != null) {
            day = bundle.getString("day");
        }

        //Get the exercises from the specific days from DatabaseHelper and show them
        if (day == "monday") {
            Cursor cursor = mDatabaseHelper.getMonday();
            ArrayList<String> listData = new ArrayList<>();
            while (cursor.moveToNext()) {
                listData.add(cursor.getString(2));
            }
            //Create the list adapter and set the id
            final ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
            mListView.setAdapter(adapter);

        }else if (day == "tuesday") {
            Cursor cursor = mDatabaseHelper.getTuesday();
            ArrayList<String> listData = new ArrayList<>();
            while (cursor.moveToNext()) {
                listData.add(cursor.getString(2));
            }
            //Create the list adapter and set the id
            final ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
            mListView.setAdapter(adapter);

        }else if (day == "wednesday") {
            Cursor cursor = mDatabaseHelper.getWednesday();
            ArrayList<String> listData = new ArrayList<>();
            while (cursor.moveToNext()) {
                listData.add(cursor.getString(2));
            }
            //Create the list adapter and set the id
            final ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
            mListView.setAdapter(adapter);

        }else if (day == "thursday") {
            Cursor cursor = mDatabaseHelper.getThursday();
            ArrayList<String> listData = new ArrayList<>();
            while (cursor.moveToNext()) {
                listData.add(cursor.getString(2));
            }
            //Create the list adapter and set the id
            final ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
            mListView.setAdapter(adapter);

        }else if (day == "friday") {
            Cursor cursor = mDatabaseHelper.getFriday();
            ArrayList<String> listData = new ArrayList<>();
            while (cursor.moveToNext()) {
                listData.add(cursor.getString(2));
            }
            //Create the list adapter and set the id
            final ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
            mListView.setAdapter(adapter);

        }else if (day == "saturday") {
            Cursor cursor = mDatabaseHelper.getSaturday();
            ArrayList<String> listData = new ArrayList<>();
            while (cursor.moveToNext()) {
                listData.add(cursor.getString(2));
            }
            //Create the list adapter and set the id
            final ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
            mListView.setAdapter(adapter);

        }else if (day == "sunday") {
            Cursor cursor = mDatabaseHelper.getSunday();
            ArrayList<String> listData = new ArrayList<>();
            while (cursor.moveToNext()) {
                listData.add(cursor.getString(2));
            }
            //Create the list adapter and set the id
            final ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
            mListView.setAdapter(adapter);
        }


        //OnClick for the ExerciseListItem
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = parent.getItemAtPosition(position).toString();

                //Get ID associated with name
                Cursor data = mDatabaseHelper.getItemID(name);
                int itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                //If there is such an ID display it
                if (itemID > -1) {
                    Fragment fragment = new ExerciseListItemFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", itemID);
                    bundle.putString("name", name);
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

                } else {
                    Toast.makeText(getActivity(), "No ID with that name", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}

