package dk.flemminglarsen.easyfitplan.Helperclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dk.flemminglarsen.easyfitplan.R;


public class FoodItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<FoodActivity> arrayList;

    public FoodItemAdapter(Context context, ArrayList<FoodActivity> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.listview_item_food, null);
        TextView food = convertView.findViewById(R.id.food);
        TextView proteins = convertView.findViewById(R.id.proteins);
        TextView fats = convertView.findViewById(R.id.fats);
        TextView carbs = convertView.findViewById(R.id.carbs);

        FoodActivity foodActivity = arrayList.get(position);
        food.setText(foodActivity.getName());
        proteins.setText(foodActivity.getProtein());
        fats.setText(foodActivity.getFats());
        carbs.setText(foodActivity.getCarbohydrates());

        return convertView;
    }
}

