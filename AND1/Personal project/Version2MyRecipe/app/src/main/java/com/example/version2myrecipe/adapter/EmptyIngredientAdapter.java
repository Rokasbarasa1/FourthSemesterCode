package com.example.version2myrecipe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.models.Ingredient;

import java.util.List;

public class EmptyIngredientAdapter extends RecyclerView.Adapter<EmptyIngredientAdapter.ViewHolder>{
    List<Ingredient> ingredients;
    OnEditTextListener listener;

    public EmptyIngredientAdapter(List<Ingredient> ingredients, OnEditTextListener listener){
        this.ingredients = ingredients;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmptyIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.piece_create_ingredient, parent, false);
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
            name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    listener.onEdit(getAdapterPosition(), name.getText().toString());
                }
            });
            name = itemView.findViewById(R.id.ingredient_text);
        }
    }

    public interface OnEditTextListener{
        void onEdit(int position, String text);
    }
}
