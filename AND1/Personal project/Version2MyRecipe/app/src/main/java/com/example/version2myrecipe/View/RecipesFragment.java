package com.example.version2myrecipe.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.version2myrecipe.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RecipesFragment extends Fragment implements TagAdapter.OnListItemClickListener {
    Animation rotateOpen;
    Animation rotateClose;
    Animation fromBottom;
    Animation toBottom;
    FloatingActionButton add_btn;
    FloatingActionButton trash_btn;
    FloatingActionButton create_btn;
    boolean clicked = false;
    RecyclerView tagList;
    TagAdapter tagAdapter;

    public RecipesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_recipes, container, false);

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("kaka"));
        tags.add(new Tag("Mouse"));
        tags.add(new Tag("pet"));
        tags.add(new Tag("Dog"));
        tags.add(new Tag("Cow"));
        tags.add(new Tag("Rat"));
        tags.add(new Tag("Corona"));
        tagList = rootView.findViewById(R.id.rv);
        tagList.hasFixedSize();
        tagList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        tagAdapter = new TagAdapter(tags, this);
        tagList.setAdapter(tagAdapter);

        //Expandable button thing
        add_btn = rootView.findViewById(R.id.add_btn);
        trash_btn = rootView.findViewById(R.id.trash_btn);
        create_btn = rootView.findViewById(R.id.create_btn);
        rotateOpen = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.to_bottom_anim);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });
        trash_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(getActivity(), Login.class);
                menuIntent.putExtra("name", 21);
                startActivity(menuIntent);
            }
        });
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(rootView.getContext(), "Clicked create button", Toast.LENGTH_SHORT).show();
                Intent menuIntent = new Intent(getActivity(), RecipeScreen.class);
                startActivity(menuIntent);
            }
        });
        return rootView;
    }

    private void onAddButtonClicked() {
        setVisibility(clicked);
        setAnimation(clicked);
        if(!clicked)
            clicked = true;
        else
            clicked = false;
    }

    @SuppressLint("RestrictedApi")
    private void setVisibility(Boolean clicked) {
        if(!clicked){
            trash_btn.setVisibility(View.VISIBLE);
            create_btn.setVisibility(View.VISIBLE);
        } else {
            trash_btn.setVisibility(View.INVISIBLE);
            create_btn.setVisibility(View.INVISIBLE);
        }
    }

    private void setAnimation(Boolean clicked) {
        if(!clicked){
            trash_btn.startAnimation(fromBottom);
            create_btn.startAnimation(fromBottom);
            add_btn.startAnimation(rotateOpen);
        }else {
            trash_btn.startAnimation(toBottom);
            create_btn.startAnimation(toBottom);
            add_btn.startAnimation(rotateClose);
        }
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getContext(), ""+ position,Toast.LENGTH_SHORT).show();
    }
}