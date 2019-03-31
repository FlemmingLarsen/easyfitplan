package dk.flemminglarsen.easyfitplan.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import dk.flemminglarsen.easyfitplan.Helperclasses.FoodActivity;
import dk.flemminglarsen.easyfitplan.Helperclasses.TrackingActivity;
import dk.flemminglarsen.easyfitplan.R;

import static android.content.Context.MODE_PRIVATE;


public class FoodFragment extends Fragment {

    ImageButton addFood;
    String foods, carbo, protein, fats;
    ListView listView;
    ArrayList<String> itemList;
    ArrayAdapter<String> adapter;
    Button test;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        addFood = view.findViewById(R.id.addFood);
        listView = view.findViewById(R.id.caloriesEaten);
        test = view.findViewById(R.id.test);


        if(itemList == null){
            itemList = new ArrayList<>();
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);
        getInfo();


        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new TrackingFragment());
                fr.addToBackStack(null).commit();
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(foods != null) {
                    itemList.add(foods);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        return view;
    }

    public void getInfo(){
        TrackingActivity trackingActivity = new TrackingActivity();
        foods = trackingActivity.getName();
        carbo = trackingActivity.getCarbohydrates();
        protein = trackingActivity.getProtein();
        fats = trackingActivity.getFats();

    }

    public void saveInfo(){

    }

    public void loadInfo(){

    }
}
