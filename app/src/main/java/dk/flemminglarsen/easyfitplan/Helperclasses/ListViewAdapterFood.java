package dk.flemminglarsen.easyfitplan.Helperclasses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.flemminglarsen.easyfitplan.R;

public class ListViewAdapterFood extends ArrayAdapter<String> {

    List list = new ArrayList();
    private String food;
    private String proteins;
    private String fats;
    private String carbohydrates;
    private Context mContext;

    public ListViewAdapterFood(@NonNull Context context, String food, String proteins, String fats, String carbohydrates) {
        super(context, R.layout.listview_item_food);


        this.food = food;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return list!=null? food.length() : 0;
    }


    @Override
    public void add(@Nullable String object) {
        super.add(object);
        list.add(object);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if(convertView == null) {
            LayoutInflater mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflator.inflate(R.layout.listview_item_food, parent, false);

            mViewHolder.food = convertView.findViewById(R.id.food);
            mViewHolder.proteins = convertView.findViewById(R.id.protein);
            mViewHolder.fats = convertView.findViewById(R.id.fats);
            mViewHolder.carbohydrates = convertView.findViewById(R.id.carbo);

            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder)convertView.getTag();
        }

        mViewHolder.food.setText(food);
        mViewHolder.proteins.setText(proteins);
        mViewHolder.fats.setText(fats);
        mViewHolder.carbohydrates.setText(carbohydrates);

        return convertView;
    }

    static class ViewHolder{
        TextView food;
        TextView proteins;
        TextView fats;
        TextView carbohydrates;
    }

}
