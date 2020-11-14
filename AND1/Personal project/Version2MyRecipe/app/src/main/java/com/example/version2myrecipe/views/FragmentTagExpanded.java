package com.example.version2myrecipe.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.adapter.RecipeAdapter;
import com.example.version2myrecipe.adapter.TagAdapter;
import com.example.version2myrecipe.models.Recipe;
import com.example.version2myrecipe.models.Tag;
import com.example.version2myrecipe.viewModels.TagsExpandedViewModel;
import com.example.version2myrecipe.viewModels.TagsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FragmentTagExpanded extends Fragment {
    Animation rotateOpen;
    Animation rotateClose;
    Animation fromBottom;
    Animation toBottom;
    FloatingActionButton add_btn;
    FloatingActionButton trash_btn;
    FloatingActionButton create_btn;
    boolean clicked = false;
    RecyclerView recipeList;
    RecipeAdapter recipeAdapter;
    TagsExpandedViewModel tagsExpandedViewModel;
    Tag currentTag;

    public FragmentTagExpanded(Tag currentTag) {
        this.currentTag = currentTag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tag_expanded, container, false);

        //Tag List
        initRecipesRecyclerView(rootView);

        //Expandable button
        setUpExpandableFloatingButton(rootView);
        return rootView;
    }

    private void initRecipesRecyclerView(View rootView){
        tagsExpandedViewModel = ViewModelProviders.of(this).get(TagsExpandedViewModel.class);
        tagsExpandedViewModel.init();
        tagsExpandedViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> tags) {
                recipeAdapter.notifyDataSetChanged();
            }
        });

        recipeList = rootView.findViewById(R.id.rv_expanded);
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recipeAdapter = new RecipeAdapter(tagsExpandedViewModel.getRecipes().getValue());
        recipeList.setAdapter(recipeAdapter);
    }

    public void setTag(Tag currentTag){
        this.currentTag = currentTag;
    }

    private void setUpExpandableFloatingButton(final View rootView){
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
                Intent menuIntent = new Intent(getActivity(), ActivityLogin.class);
                menuIntent.putExtra("name", 21);
                startActivity(menuIntent);
            }
        });
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(rootView.getContext(), "Clicked create button", Toast.LENGTH_SHORT).show();
                Intent menuIntent = new Intent(getActivity(), ActivityCreateRecipe.class);
                startActivity(menuIntent);
            }
        });
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
}