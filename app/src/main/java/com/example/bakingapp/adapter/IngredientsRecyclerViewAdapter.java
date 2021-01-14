package com.example.bakingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.model.Ingredient;

import java.util.List;

public class IngredientsRecyclerViewAdapter extends RecyclerView.Adapter<IngredientsRecyclerViewAdapter.IngredientsViewHolder> {

    private List<Ingredient> ingredients;
    private Context mContext;

    public IngredientsRecyclerViewAdapter(Context mContext, List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ingredients, parent, false);
        IngredientsRecyclerViewAdapter.IngredientsViewHolder viewHolder = new IngredientsRecyclerViewAdapter.IngredientsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.ingName.setText(ingredients.get(position).getIngredient());
        holder.ingDetails.setText(ingredients.get(position).getQuantity() + " " + ingredients.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class IngredientsViewHolder extends RecyclerView.ViewHolder{

        TextView ingName, ingDetails;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);

            ingName = itemView.findViewById(R.id.tv_ingredients_name);
            ingDetails = itemView.findViewById(R.id.tv_ingredient_details);

        }
    }

}
