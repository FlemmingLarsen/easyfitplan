package dk.flemminglarsen.easyfitplan.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dk.flemminglarsen.easyfitplan.Helperclasses.FoodActivity;
import dk.flemminglarsen.easyfitplan.Helperclasses.FoodAdapter;
import dk.flemminglarsen.easyfitplan.R;

public class TrackingFragment extends Fragment {

    DatabaseReference databaseReference;
    ArrayList<FoodActivity> list;
    RecyclerView recyclerView;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("foods");
        recyclerView = view.findViewById(R.id.rv);
        searchView = view.findViewById(R.id.searchFood);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        //If database reference is not empty, get snapshot from database and add the values to ArrayList
        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            list.add(ds.getValue(FoodActivity.class));
                        }
                        FoodAdapter adapter = new FoodAdapter(list);
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        //If searchView is not empty, listen for callbacks for changes to the query text
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }

    //If an object in the ArrayList contains the letter(s) as in the searchView, add that item to a new arrayList
    private void search(String str) {
        ArrayList<FoodActivity> searchList = new ArrayList<>();
        for (FoodActivity object : list) {
            if (object.getName().toLowerCase().contains(str.toLowerCase())) {
                searchList.add(object);
            }
        }
        FoodAdapter foodAdapter = new FoodAdapter(searchList);
        recyclerView.setAdapter(foodAdapter);
    }
}
