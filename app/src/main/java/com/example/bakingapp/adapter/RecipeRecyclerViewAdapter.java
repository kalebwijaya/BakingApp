package com.example.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.view.DetailsActivity;
import com.example.bakingapp.R;
import com.example.bakingapp.model.Recipe;

import java.util.List;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Recipe> mData;

    public RecipeRecyclerViewAdapter(Context mContext, List<Recipe> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recipe, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_recipeName.setText(mData.get(position).getName());
        holder.tv_recipeTotalSteps.setText("Steps : " + mData.get(position).getSteps().size());
        holder.tv_recipeTotalServing.setText("Servings : " + mData.get(position).getServings());
        holder.tv_recipeTotalIngredients.setText("Total Ingredients : " + mData.get(position).getIngredients().size());

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("recipeID", mData.get(position).getID());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_recipeImage;
        private ConstraintLayout itemLayout;
        private TextView tv_recipeName, tv_recipeTotalIngredients, tv_recipeTotalSteps, tv_recipeTotalServing;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            iv_recipeImage = itemView.findViewById(R.id.iv_recipe_image);
            tv_recipeName = itemView.findViewById(R.id.tv_recipe_name);
            tv_recipeTotalIngredients = itemView.findViewById(R.id.tv_total_ingredients);
            tv_recipeTotalServing = itemView.findViewById(R.id.tv_total_serving);
            tv_recipeTotalSteps = itemView.findViewById(R.id.tv_total_step);
            itemLayout = itemView.findViewById(R.id.item_layout);

        }
    }
}
