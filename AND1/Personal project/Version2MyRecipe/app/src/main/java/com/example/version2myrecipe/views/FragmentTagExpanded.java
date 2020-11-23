package com.example.version2myrecipe.views;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.adapter.AdapterRecipe;
import com.example.version2myrecipe.models.Recipe;
import com.example.version2myrecipe.models.Tag;
import com.example.version2myrecipe.viewModels.TagsExpandedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FragmentTagExpanded extends Fragment implements AdapterRecipe.OnListRecipeClickListener {
    private FloatingActionButton add_btn;
    private RecyclerView recipeList;
    private AdapterRecipe adapterRecipe;
    private TagsExpandedViewModel viewModel;
    private Tag currentTag;
    private FragmentManager supportFragmentManager;
    private TextView toolbarTitle;
    private ActionBar upArrow;


    public FragmentTagExpanded(FragmentManager supportFragmentManager, TextView toolbarTitle, ActionBar upArrow, Tag currentTag) {
        this.currentTag = currentTag;
        this.supportFragmentManager = supportFragmentManager;
        this.toolbarTitle = toolbarTitle;
        this.upArrow = upArrow;
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
        viewModel = ViewModelProviders.of(this).get(TagsExpandedViewModel.class);
        viewModel.init(currentTag);
        viewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> tags) {
                adapterRecipe.notifyDataSetChanged();
            }
        });

        recipeList = rootView.findViewById(R.id.rv_expanded);
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        adapterRecipe = new AdapterRecipe(viewModel.getRecipes().getValue(), this);
        recipeList.setAdapter(adapterRecipe);
    }

    public void setTag(Tag currentTag){
        this.currentTag = currentTag;
    }

    private void setUpExpandableFloatingButton(final View rootView){
        add_btn = rootView.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new FragmentCreateRecipe(currentTag.getName());
                toolbarTitle.setText("Create recipe");
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                upArrow.setDisplayHomeAsUpEnabled(true);
            }
        });
    }

    @Override
    public void onClick(int position, String name) {
        Fragment fragment = null;
        fragment = new FragmentSeeRecipe(name);
        toolbarTitle.setText("View recipe");
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        upArrow.setDisplayHomeAsUpEnabled(true);
    }
}