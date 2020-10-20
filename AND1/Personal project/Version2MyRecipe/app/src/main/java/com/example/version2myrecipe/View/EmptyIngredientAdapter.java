package com.example.version2myrecipe.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.version2myrecipe.R;

import java.util.List;

public class EmptyIngredientAdapter extends RecyclerView.Adapter<EmptyIngredientAdapter.ViewHolder>{
    List<Ingredient> ingredients;

    public EmptyIngredientAdapter(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public EmptyIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ingredient_part_layout, parent, false);
        return new EmptyIngredientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmptyIngredientAdapter.ViewHolder viewHolder, int position) {
        viewHolder.name.setText(ingredients.get(position).getEmpty());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingredient_text);
        }
    }
}
