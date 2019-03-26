package dk.flemminglarsen.easyfitplan.Fragments.Weekdays;

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


public class TuesdayFragment extends Fragment {

    private static final String TAG = "ListDataActivity";
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monday, container, false);

        mListView = view.findViewById(R.id.listview);
        mDatabaseHelper = new DatabaseHelper(getActivity());

        populateListView();

        return view;
    }

    //Get data from column 1 and add it to the listview
    private void populateListView() {
        Cursor data = mDatabaseHelper.getTuesday();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(1));
        }
    //Create the list adapter and set the it
        final ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //OnClick for the listItem
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), "You clicked on " + name, Toast.LENGTH_SHORT).show();

                //Get ID associated with name
                Cursor data = mDatabaseHelper.getItemID(name);
                int itemID = -1;
                while (data.moveToNext()){
                    itemID = data.getInt(0);
                }
                //If there is such an ID display it or not
                if(itemID > -1){
                    Toast.makeText(getActivity(), "The ID is: " + itemID, Toast.LENGTH_SHORT).show();
                    Fragment fragment = new ExerciseListItemFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", itemID);
                    bundle.putString("name", name);
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

                }else{
                    Toast.makeText(getActivity(), "No ID with that name", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
