package dk.flemminglarsen.easyfitplan.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dk.flemminglarsen.easyfitplan.Helperclasses.FoodDatabaseHelper;
import dk.flemminglarsen.easyfitplan.R;


public class FoodDetailFragment extends Fragment {

    String food, proteins, fats, carbohydrates;
    TextView foodText, proteinText, carboText, fatsText;
    Button addFood;
    FoodDatabaseHelper mFoodDatabaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_detail, container, false);

        foodText = view.findViewById(R.id.foodName);
        proteinText = view.findViewById(R.id.protein);
        carboText = view.findViewById(R.id.carbo);
        fatsText = view.findViewById(R.id.fats);
        addFood = view.findViewById(R.id.addFood);

        Bundle bundle = getArguments();
        if(bundle != null){
            //Set Textviews to values
            foodText.setText(bundle.getString("foods"));
            proteinText.setText(bundle.getString("protein"));
            carboText.setText(bundle.getString("carbo"));
            fatsText.setText(bundle.getString("fats"));

            //Set string to values
            food = (bundle.getString("foods"));
            proteins = (bundle.getString("protein"));
            fats = (bundle.getString("carbo"));
            carbohydrates = (bundle.getString("fats"));

            mFoodDatabaseHelper = new FoodDatabaseHelper(getActivity());
        }

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData(food, proteins, fats, carbohydrates);
            }
        });

        return view;
    }

    //Add data to SQLite
    public void AddData(String food, String proteins, String fats, String carbohydrates){
        boolean insertData = mFoodDatabaseHelper.addData(food, proteins, fats, carbohydrates);

        if(insertData == true) {
            Toast.makeText(getActivity(), food + " Added ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        mFoodDatabaseHelper.close();
    }



}
