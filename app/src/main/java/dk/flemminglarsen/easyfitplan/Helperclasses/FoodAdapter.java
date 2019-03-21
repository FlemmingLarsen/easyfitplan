package dk.flemminglarsen.easyfitplan.Helperclasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dk.flemminglarsen.easyfitplan.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{

    ArrayList<FoodActivity> list;
    public FoodAdapter(ArrayList<FoodActivity> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item_food, parent, false);
        return new ViewHolder(view);
    }

    //WHY ARE FOODS GETTING CARBS?!?!?
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.foods.setText(list.get(position).getName());
        holder.carbo.setText(list.get(position).getCarbohydrates());
        holder.protein.setText(list.get(position).getProtein());
        holder.fats.setText(list.get(position).getFats());
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
