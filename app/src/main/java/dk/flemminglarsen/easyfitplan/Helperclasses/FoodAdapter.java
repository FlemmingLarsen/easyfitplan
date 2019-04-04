package dk.flemminglarsen.easyfitplan.Helperclasses;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.core.Context;

import java.util.ArrayList;

import dk.flemminglarsen.easyfitplan.Activities.MenuActivity;
import dk.flemminglarsen.easyfitplan.Fragments.FoodDetailFragment;
import dk.flemminglarsen.easyfitplan.Fragments.FoodFragment;
import dk.flemminglarsen.easyfitplan.Fragments.TrackingFragment;
import dk.flemminglarsen.easyfitplan.R;



public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    String foods, carbo, protein, fats;
    ArrayList<FoodActivity> list;
    Context context;
    FoodDatabaseHelper mFoodDatabaseHelper;


    public FoodAdapter(Context context, ArrayList<FoodActivity> list) {
        this.list = list;
        this.context = context;
    }

    public FoodAdapter(ArrayList<FoodActivity> list){
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item_food, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.foods.setText(list.get(position).getName());
        holder.carbo.setText(list.get(position).getCarbohydrates());
        holder.protein.setText(list.get(position).getProtein());
        holder.fats.setText(list.get(position).getFats());



        //Onclick for the TrackingFragment recyclerview from SQLite database
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get data
                foods = list.get(position).getName();
                carbo = list.get(position).getCarbohydrates();
                protein = list.get(position).getProtein();
                fats = list.get(position).getFats();

                //Send data to FoodDetailFragment
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment fragment = new FoodDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("foods", foods);
                bundle.putString("carbo", carbo);
                bundle.putString("protein", protein);
                bundle.putString("fats", fats);
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

            }
        });
    }
    
    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView foods, carbo, fats, protein;

        public ViewHolder(View itemView) {
            super(itemView);
            carbo = itemView.findViewById(R.id.carbo);
            protein = itemView.findViewById(R.id.protein);
            fats = itemView.findViewById(R.id.fats);
            foods = itemView.findViewById(R.id.food);
        }
    }
}

