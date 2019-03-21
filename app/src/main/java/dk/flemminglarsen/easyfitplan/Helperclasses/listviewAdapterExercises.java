package dk.flemminglarsen.easyfitplan.Helperclasses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import dk.flemminglarsen.easyfitplan.R;

public class listviewAdapterExercises extends ArrayAdapter<String> {

    private String [] names;
    private int [] gifs;
    private Context mContext;

    public listviewAdapterExercises(@NonNull Context context, String[] exerciseNames, int[] exerciseGifs, String[] chestExerciseDescription) {
        super(context, R.layout.listview_item_exercises);

        this.names = exerciseNames;
        this.gifs = exerciseGifs;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if(convertView == null) {
            LayoutInflater mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflator.inflate(R.layout.listview_item_exercises, parent, false);
            mViewHolder.mExercise = convertView.findViewById(R.id.imageView);
            mViewHolder.mName = convertView.findViewById(R.id.textView);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder)convertView.getTag();
        }
            mViewHolder.mExercise.setImageResource(gifs[position]);
            mViewHolder.mName.setText(names[position]);

        return convertView;
    }

    static class ViewHolder{
        ImageView mExercise;
        TextView mName;
    }
}
