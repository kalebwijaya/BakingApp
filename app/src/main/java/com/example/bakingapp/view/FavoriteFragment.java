package com.example.bakingapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.adapter.RecipeRecyclerViewAdapter;
import com.example.bakingapp.model.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener{
    private View v;
    private RecyclerView rvFavorite;
    private RecipeRecyclerViewAdapter recyclerViewAdapter;
    private List<Recipe> dataSet = new ArrayList<>();;
    private List<Integer> favID = new ArrayList<>();
    public static Context contextOfApplication;
    private SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFavorite();

        contextOfApplication = getContext();

        recyclerViewAdapter = new RecipeRecyclerViewAdapter(getContext(),dataSet);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_favorite, container, false);
        rvFavorite = v.findViewById(R.id.rv_favorite);
        rvFavorite.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFavorite.setAdapter(recyclerViewAdapter);


        prefs.registerOnSharedPreferenceChangeListener(this);

        return v;
    }

    @Override
    public void onPause() {
        prefs.unregisterOnSharedPreferenceChangeListener(this);
        getFavorite();
        recyclerViewAdapter.notifyDataSetChanged();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        prefs.registerOnSharedPreferenceChangeListener(this);
        getFavorite();
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void getFavorite(){
        List<Recipe> tempData = new ArrayList<>();
        prefs = getContext().getSharedPreferences("Shared Preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("recipes",null);
        String favIDJson = prefs.getString("favID",null);

        Type type = new TypeToken<List<Recipe>>() {}.getType();
        Type favType = new TypeToken<List<Integer>>() {}.getType();

        favID = gson.fromJson(favIDJson, favType);
        tempData = gson.fromJson(json, type);

        if (tempData != null && favID != null){
            dataSet.clear();
            for (Recipe recipe : tempData){
                for (int id : favID){
                    if (recipe.getID() == id)
                        dataSet.add(recipe);
                }
            }
        }

        Log.d("Fav","Data Get From Shared = " + dataSet.size());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        getFavorite();
        recyclerViewAdapter.notifyDataSetChanged();
    }


}
