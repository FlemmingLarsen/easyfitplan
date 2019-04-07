package dk.flemminglarsen.easyfitplan.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import dk.flemminglarsen.easyfitplan.Helperclasses.FoodDatabaseHelper;
import dk.flemminglarsen.easyfitplan.R;



public class FoodFragment extends Fragment {

    String food, proteins, fats, carbohydrates;
    ImageButton addFood;
    FoodDatabaseHelper foodDatabaseHelper;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<HashMap<String, String>> foodlist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        addFood = view.findViewById(R.id.addFood);
        listView = view.findViewById(R.id.caloriesEaten);
        foodDatabaseHelper = new FoodDatabaseHelper(getActivity());
        sqLiteDatabase = foodDatabaseHelper.getWritableDatabase();


        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackingFragment frt = new TrackingFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, frt);
                transaction.addToBackStack(null).commit();
            }
        });

        foodlist = new ArrayList<>();
        populateListview();

        return view;
    }

    private void populateListview() {

        //Get database reference, if it's not empty, get values from every item
        Cursor cursor = foodDatabaseHelper.getData();
        foodlist.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                food = cursor.getString(1);
                proteins = cursor.getString(2);
                fats = cursor.getString(3);
                carbohydrates = cursor.getString(4);

                //Put values into a Hashmap store it in an ArrayList
                HashMap<String, String> foods = new HashMap<>();
                foods.put("foods", food);
                foods.put("proteins", proteins);
                foods.put("fats", fats);
                foods.put("carbohydrates", carbohydrates);
                foodlist.add(foods);
                cursor.moveToNext();

            }
            //user adapter to put values into the UI
            ListAdapter adapter = new SimpleAdapter(getActivity(), foodlist, R.layout.fragment_food_items, new String[]{"foods", "proteins", "fats", "carbohydrates"}, new int[]{R.id.food, R.id.proteins, R.id.fats, R.id.carbs});
            listView.setAdapter(adapter);
        }
    }
}




