package com.example.bakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Ingredient;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Step;

import java.util.List;

public class DetailsRecyclerViewAdapter extends RecyclerView.Adapter<DetailsRecyclerViewAdapter.MyViewHolder> {

    private Recipe recipe;
    private Context mContext;

    public DetailsRecyclerViewAdapter(Recipe recipe, Context mContext) {
        this.recipe = recipe;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_details, parent, false);
        DetailsRecyclerViewAdapter.MyViewHolder viewHolder = new DetailsRecyclerViewAdapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (position == 0){
            setIngRecyclerView(holder.rvDetails, recipe.getIngredients());
        }else if(position == 1){
            setStepRecyclerView(holder.rvDetails, recipe.getSteps());
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        RecyclerView rvDetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rvDetails = itemView.findViewById(R.id.rv_item_details);

        }
    }

    private void setIngRecyclerView(RecyclerView recyclerView, List<Ingredient> ingredients){
        IngredientsRecyclerViewAdapter recyclerViewAdapter = new IngredientsRecyclerViewAdapter(mContext, ingredients);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void setStepRecyclerView(RecyclerView recyclerView, List<Step> steps){
        StepRecyclerViewAdapter recyclerViewAdapter = new StepRecyclerViewAdapter(steps, mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

}
