package com.example.version2myrecipe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.models.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    List<Recipe> recipes;

    public RecipeAdapter(List<Recipe> tags){
        this.recipes = tags;
    }


    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.piece_tag, parent, false);
        return new RecipeAdapter.ViewHolder(view);

    }

    public void onBindViewHolder(RecipeAdapter.ViewHolder viewHolder, int position){
        viewHolder.name.setText(recipes.get(position).get);
    }

    public int getItemCount(){
        return tags.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
            name = itemView.findViewById(R.id.tag_text);
        }
    }

    public interface OnListItemClickListener{
        void onClick(int position);
    }
}
