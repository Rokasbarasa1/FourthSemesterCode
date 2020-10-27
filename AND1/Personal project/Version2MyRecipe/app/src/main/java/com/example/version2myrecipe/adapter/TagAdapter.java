package com.example.version2myrecipe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.models.Tag;

import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {
    List<Tag> tags;
    OnListItemClickListener listener;
    public TagAdapter(List<Tag> tags, OnListItemClickListener listener){
        this.tags = tags;
        this.listener = listener;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.piece_tag, parent, false);
        return new ViewHolder(view);

    }

    public void onBindViewHolder(ViewHolder viewHolder, int position){
        viewHolder.name.setText(tags.get(position).getName());
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
            name = itemView.findViewById(R.id.add_tag_text);
        }
    }

    public interface OnListItemClickListener{
        void onClick(int position);
    }
}
