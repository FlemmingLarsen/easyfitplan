package dk.flemminglarsen.easyfitplan.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import dk.flemminglarsen.easyfitplan.Helperclasses.FoodDatabaseHelper;
import dk.flemminglarsen.easyfitplan.R;

import static java.lang.Integer.valueOf;


public class FoodFragment extends Fragment {

    String id, food, proteins, fats, carbohydrates, maintain;
    int caloriesTotal, maintainInt, percent;
    ImageButton addFood;
    FoodDatabaseHelper foodDatabaseHelper;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<HashMap<String, String>> foodlist;
    TextView textView, calories ;
    ListAdapter adapter;
    HashMap<String, String> foods;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        addFood = view.findViewById(R.id.addFood);
        listView = view.findViewById(R.id.caloriesEaten);
        foodDatabaseHelper = new FoodDatabaseHelper(getActivity());
        sqLiteDatabase = foodDatabaseHelper.getWritableDatabase();
        textView = view.findViewById(R.id.caloriesTotal);
        progressBar = view.findViewById(R.id.progressBar);


        foodlist = new ArrayList<>();
        populateListview();
        getValues();
        getCalories();
        setProgressBar();

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackingFragment frt = new TrackingFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, frt);
                transaction.addToBackStack(null).commit();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {

                //Alertdialog before item is deleted
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Do you want to delete this item?");
                alertDialogBuilder
                        .setMessage("You can always add it again later")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                //Get foods hashmap, get value from key, and call deleteItem()
                                foods = (HashMap<String, String>) adapter.getItem(position);
                                if(foods != null) {
                                    String name = foods.get("id");
                                    int delete = valueOf(name);
                                    deleteItem(delete);
                                    Toast.makeText(getActivity(), "id: " + name, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        //Closes the dialog
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        return view;
    }

    //Populate listview with food saves in databse
    private void populateListview() {

        //Get database reference, if it's not empty, get values from every item
        Cursor cursor = foodDatabaseHelper.getData();
        foodlist.clear();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                id = cursor.getString(0);
                food = cursor.getString(1);
                proteins = cursor.getString(2);
                fats = cursor.getString(3);
                carbohydrates = cursor.getString(4);

                //Put values into a Hashmap
                foods = new HashMap<>();
                foods.put("id", id);
                foods.put("foods", food);
                foods.put("proteins", proteins);
                foods.put("fats", fats);
                foods.put("carbohydrates", carbohydrates);
                foodlist.add(foods);
                cursor.moveToNext();
            }
            //user adapter to put values into the UI
            adapter = new SimpleAdapter(getActivity(), foodlist, R.layout.fragment_food_items, new String[]{"foods", "proteins", "fats", "carbohydrates"}, new int[]{R.id.food, R.id.proteins, R.id.fats, R.id.carbs});
            listView.setAdapter(adapter);
        }
    }

    //Delete item from listview and populate Listview again and update progressbar
    public void deleteItem(int position){
        foodDatabaseHelper.deleteItem(position);
        populateListview();
        getValues();
        setProgressBar();
    }

    //Get values from each column and calculate to amount of calories
    public void getValues(){
        int proteinCursor = foodDatabaseHelper.getProtein();
        int fatCursor = foodDatabaseHelper.getFat();
        int carbsCursor = foodDatabaseHelper.getcarbs();
        caloriesTotal = ((proteinCursor * 4) + (carbsCursor * 4) + (fatCursor * 9));

        if(caloriesTotal != 0) {
            textView.setText(String.valueOf(caloriesTotal));
        }else{
            textView.setText("You haven't eaten anything today!");
        }
    }

    //Get maintain value calculated in Profilefragment from shared preferences
    public void getCalories() {
        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        maintain = pref.getString("calories", "empty");
        maintainInt = Integer.valueOf(maintain);
    }

    //Calculate percent eaten out of total maintain and update progressbar
    public void setProgressBar(){
        percent = ((caloriesTotal * 100) / maintainInt);
        progressBar.setProgress(percent);

    }
}




